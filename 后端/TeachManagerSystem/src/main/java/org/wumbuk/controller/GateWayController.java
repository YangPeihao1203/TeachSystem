package org.wumbuk.controller;


import org.wumbuk.entity.*;
import org.wumbuk.service.*;
import org.wumbuk.utils.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/gateway")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class GateWayController {
    @Autowired
    UserService userService;


    /**
     * 在后端生成图形验证码，
     * 直接把base64形式图片给前端进行返回
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, value = "sendgraphcode")
    @ResponseBody
    public Msg sendgraphcode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("开始执行了sendgraphcode方法");
        Msg msg = new Msg();
        HttpSession session = request.getSession(true);
        Cookie cookie = new Cookie("COOKIESESSIONID", session.getId());
        if(session.isNew()){
            System.out.println("该session是新建立的");
        }else {
            System.out.println("该session是后来建立的");
            session.removeAttribute("code");
        }

        try {
            GraphicVerificationUtil graphicVerificationUtil = GraphicVerificationUtil.Graphic(request);
            session.setAttribute("code", graphicVerificationUtil.getsRand().substring(graphicVerificationUtil.getsRand().length() - 4,
                    graphicVerificationUtil.getsRand().length()));
            System.out.println("得到的密码为"+session.getAttribute("code"));
            String imgStr = ImageToBase64Util.getImgStr(graphicVerificationUtil.getGraphicAddress());
            imgStr = ("data:image/jpeg;base64," + imgStr);
            msg.setCode(0);
            msg.setMsg("生成验证码成功");
            msg.add("imgStr", imgStr);
            System.out.println("得到的sessionId为:"+session.getId());
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生错误");
            msg.setMsg("内部服务器错误");
            msg.setCode(1);
            return msg;
        }
    }


    /**
     * 发送短信
     * @param user
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "sendmessage")
    @ResponseBody
    public Msg sendMessage(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("开始执行了sendmessage的函方法");
        Msg msg = new Msg();
        System.out.println("得到的用户user信息为");
        System.out.println(user);
        String phone = userService.getPhoneByIdAndRole(user);
        HttpSession session = request.getSession(true);

        if(null==phone){
            msg.setCode(1);
            msg.setMsg("通过用户账号和用户角色查询电话号码失败");
            return  msg;
        }
        if(session.isNew()){
            System.out.println("该session是新建立的");
        }else {
            System.out.println("该session是后来建立的");
            session.removeAttribute("id");
            session.removeAttribute("code");
        }
        Cookie cookie = new Cookie("COOKIESESSIONID", session.getId());
//        cookie.setValue(session.getId());
        System.out.println("session里面的cookie的ID为：" + session.getId());
        cookie.setPath(request.getContextPath() + "/");//设置作用域
        cookie.setMaxAge(60 * 5);
        cookie.setHttpOnly(true);

        org.wumbuk.entity.Message message = SmsService.SendMessage(phone);
        System.out.println("发送信息成功");
//        response.setHeader("Set-Cookie", "SameSite=none");
        msg.setCode(0);
        session.setAttribute("phone",phone);
        session.setAttribute("code",message.getCode());
        session.setAttribute("id",user.getId());
        session.setAttribute("role",user.getRole());
        System.out.println("发送验证码成功、发送的验证码" + message.getCode());
        response.addCookie(cookie);
        return msg;
    }



    /**
     * 用户通过账号和密码登录+图形验证码
     * @param map
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST, value = "loginbypwd")
    @ResponseBody
    public Msg loginbypwd(@RequestBody Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie cookie1=null;
        System.out.println("loginbypwd中的map为"+map);
        User user = JsonUtils.mapToobj(map, User.class);
        String code = (String) map.get("code");
        System.out.println("得到的用户为" + user);
        System.out.println("得到的验证码为" + code);
        Msg msg = new Msg();
        HttpSession session = null;
        session = request.getSession(false);
        String sessionId = null;
        try {
            sessionId = session.getId();
            System.out.println("该session是后来建立的");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("操作非法");
            msg.setMsg("操作非法");
            msg.setCode(3);
            return msg;
        }
        Cookie[] cookies = request.getCookies();//根据请求数据，找到cookie数组
        for (Cookie cookie : cookies) {
            if (cookie.getValue().equals("logoff")){
                System.out.println("已登出");
                cookie.setValue(null);
               response.addCookie(cookie);
            }}
        System.out.println("session中存储的密码为"+session.getAttribute("code"));
        if(null!=session.getAttribute("code")&&session.getAttribute("code").toString().toLowerCase().equals(code.toLowerCase())){
            System.out.println("开始执行");
            int rs=userService.login(user);
            if(rs!=0){
                session.removeAttribute("code");
                System.out.println("登录成功！");
                msg.setCode(0);
                msg.setMsg("登录成功");
                session.setMaxInactiveInterval(60 * 30*5);
                session.setAttribute("phone",user.getPhone());
                Cookie cookie2 = new Cookie("uid",Integer.toString(rs) );
                cookie2.setPath(request.getContextPath() + "/");//设置作用域
                cookie2.setMaxAge(60 * 60 * 5);
                response.addCookie(cookie2);
                return msg;
            }else{
                System.out.println("账号密码错误");
                msg.setCode(1);
                msg.setMsg("账号密码错误");
                session.removeAttribute("code");
                return msg;
            }
        }else {
            System.out.println("验证码错误");
            msg.setMsg("验证码填写错误");
            msg.setCode(2);
            return msg;
        }
    }

    /**
     * 用户通过短信验证码登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "loginbymessage")
    @ResponseBody
    public Msg loginByMessage(@RequestBody Map<String,Object> map, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("开始通过短信加验证码的方式进行登录");
        String code = (String) map.get("code");
        Integer id=Integer.parseInt(map.get("id").toString());
        Integer role=Integer.parseInt(map.get("role").toString());

        HttpSession session = request.getSession(false);
        Msg msg=new Msg();
        Cookie[] cookies = request.getCookies();
        String sessionId = null;
        try {
            sessionId = session.getId();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("得到session为空指针错误");
            msg.setMsg("验证码错误");
            msg.setCode(1);
            return msg;
        }
        System.out.println("得到的session中的code为"+session.getAttribute("code"));
//        System.out.println("得到的session中的phone为"+session.getAttribute("phone"));
        boolean idflag=false;
        boolean resultflag=false;
        for (Cookie cookie : cookies) {
            if (cookie.getValue().equals("logoff")){
                System.out.println("已登出");
                cookie.setValue(null);
                response.addCookie(cookie);
            }}
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("COOKIESESSIONID")) {
                idflag=true;
                if(session.getId().equals(cookie.getValue())&&code.equals(session.getAttribute("code").toString())
                        &&id.toString().equals(session.getAttribute("id").toString()) &&role.toString().equals(session.getAttribute("role").toString()) ){
                    resultflag=true;
                    session.setMaxInactiveInterval(60*60*5);
                    session.removeAttribute("phone");
//                    session.removeAttribute("code");
                    cookie.setPath(request.getContextPath() + "/");//设置作用域
                    cookie.setMaxAge(60*60*5);
//                    response.setHeader("Set-Cookie", "SameSite=none");
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                }else {
//                    System.out.println("测试1846");
                    System.out.println("验证码错误");
                    msg.setCode(1);
                    msg.setMsg("验证码错误");
                    return msg;
                }
            }
        }

        if(!idflag){
            System.out.println("cookie不存在了，即cookie失效了");
            msg.setCode(1);
            msg.setMsg("验证码错误");
            return msg;
        }
        if(resultflag!=true){
            System.out.println("验证码输入错误");
            msg.setCode(1);
            msg.setMsg("验证码错误");
            return msg;
        }else {
                System.out.println("验证码正确，登录成功！");
                msg.setMsg("登录成功");
                msg.setCode(0);
                Cookie cookie2 = new Cookie("uid",Integer.toString(id) );
                cookie2.setPath(request.getContextPath() + "/");//设置作用域
                cookie2.setMaxAge(60 * 60 * 5);
                response.addCookie(cookie2);
                return msg;
        }
    }


    /**
     * 该方法退出登录
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "logoff")
    public void logOff(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Msg msg = new Msg();
        System.out.println("loginOut");
        Cookie[] cookies = request.getCookies();//根据请求数据，找到cookie数组
        boolean idflag = false;
        HttpSession session=request.getSession(false);
        session.setMaxInactiveInterval(0);
        for (Cookie cookie : cookies) {
            System.out.println("cookie的名"+cookie.getName());
            cookie.setPath("/");
//            cookie.setMaxAge(0);
            cookie.setValue("logoff");
            response.addCookie(cookie);
        }


    }


    /**
     * 该方法用来获得临时密钥，供用户发布商品时图片的上传
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getcredential")
    @ResponseBody
    public Msg getCredentials(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("queryCategories方法执行了");
        Msg msg = new Msg();
        try {
            Credentials sts = STS.STS();
//            String sts=null;
            msg.setCode(0);
            System.out.println("后端得到的密钥信息为"+sts);
            msg.add("credential",sts);
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            msg.setCode(1);
            msg.setMsg("获得临时密钥失败！！！");
            return msg;
        }
    }



    /**
     * 修改用户的头像
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "updateavatar")
    @ResponseBody
    public Msg updateAvatar(@RequestBody Map<String,Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("updateAvatar");
        User user=JsonUtils.mapToobj(map,User.class);
        System.out.println("转换的user:"+user);
        int res=userService.updateAvatar(user);
        if (res==1){
            System.out.println("修改成功");
            return Msg.success();
        }

        System.out.println("修改失败");
        return Msg.fail();

    }




}
