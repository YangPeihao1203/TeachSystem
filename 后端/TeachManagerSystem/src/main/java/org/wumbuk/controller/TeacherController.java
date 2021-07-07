package org.wumbuk.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wumbuk.entity.*;
import org.wumbuk.service.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class TeacherController {


    @Autowired
    UserService userService;


    @Autowired
    CourseService courseService;


    @Autowired
    TeacherCourseService teacherCourseService;

    @Autowired
    TeacherService teacherService;


    @Autowired
    StudentService studentService;

    @Autowired
    DepatureService depatureService;

    @Autowired
    CoursestudentService coursestudentService;

    /**
     * 通过cookie取得用户的信息
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, value = "getuserinfo")
    @ResponseBody
    public Msg getUserInformation(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Msg msg = new Msg();
        String uid = null;
        System.out.println("getUserInformation 函数执行了");
        HttpSession session = request.getSession(false);
        if (null == session) {
            System.out.println("得到session是空的");
            System.out.println("用户状态非法");
            msg.setCode(1);
            msg.setMsg("用户状态非法");
            return msg;
        }
        session.setMaxInactiveInterval(60 * 60 * 30);
        Cookie[] cookies = request.getCookies();//根据请求数据，找到cookie数组
        boolean uidflag = false;
        boolean flag = false;
        for (Cookie cookie : cookies) {
            if (cookie.getValue().equals("logoff")) {
                System.out.println("已登出");
                cookie.setValue(null);
                flag = true;
            }
            if (cookie.getName().equals("uid")) {
                uidflag = true;
                uid = cookie.getValue();
                cookie.setMaxAge(60 * 60 * 30);
                response.addCookie(cookie);
            }
        }
        if (flag) {
            System.out.println("检测到已经登出");
            msg.setCode(1);
            return msg;
        }
        if (uidflag) {
            System.out.println("Controller得到的uid为" + uid);
            User userInfo = userService.getInformation(uid, 1);
            System.out.println("得到的用户数据为" + userInfo);
            msg.setCode(0);
            msg.add("user", userInfo);
            return msg;
        } else {
            System.out.println("得到cookie是空的");
            System.out.println("用户状态非法");
            msg.setCode(1);
            msg.setMsg("用户状态非法");
            return msg;
        }
    }


    /**
     * 教师查询我的课程信息
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "getcourseteacher")
    @ResponseBody
    public Msg getCourseTeacher(@RequestBody Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("getCourseTeacher 函数执行了");
        System.out.println(map);
        int currentPage = (Integer) map.getOrDefault("currentPage", 1);
        int pageSize = (Integer) map.getOrDefault("pageSize", 3);
        int id = (Integer) map.getOrDefault("id", 0);
        //1.将课程page分页
        PageHelper.startPage(currentPage, pageSize);
        //2.查询所有的课程,该老师所有教授过得课程
        List<Course> courseList = courseService.getAllCourseByTeacher(id);
        //3.返回结果
        PageInfo page = new PageInfo(courseList, 3);
        System.out.println(page);

        return Msg.success().add("PageInfo", page);

    }


    /**
     * 模糊查询用户id的所有课程
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "checkcoursebyteacher")
    @ResponseBody
    public Msg checkCourseByTeacher(@RequestBody Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("checkCourseByTeacher 函数执行了");
        System.out.println(map);
        int currentPage = (Integer) map.getOrDefault("currentPage", 1);
        int pageSize = (Integer) map.getOrDefault("pageSize", 3);
        String keyWord = map.getOrDefault("keyWord", "nothing").toString();
        int tid = Integer.parseInt(map.getOrDefault("id", "0").toString());
        //1.将课程page分页
        PageHelper.startPage(currentPage, pageSize);
        //2.查询所有模糊查询的课程
        List<Course> courseList = courseService.checkCourseFuzzyTeacher(tid, keyWord);
        //3.返回结果
        PageInfo page = new PageInfo(courseList, 3);
        System.out.println(page);
        return Msg.success().add("PageInfo", page);
    }







    /**
     * 检查用户的旧密码是否正确
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "checkoldpsw")
    @ResponseBody
    public Msg checkOldpsw( @RequestBody Map<String, Object> map,HttpServletResponse response, HttpServletRequest request) throws Exception {


        System.out.println("checkOldpsw 函数执行了");
        int id=Integer.parseInt(map.getOrDefault("id","1000").toString());
        String psw=map.getOrDefault("psw","123").toString();
        System.out.println("id:"+id);
        System.out.println("psw:"+psw);

        User user = new User();
        user.setId(id);
        user.setPsw(psw);
        user.setRole(1);
        int res=userService.login(user);
        if (res !=0) {
            System.out.println("校验成功");
            return Msg.success();
        }
        System.out.println("校验失败");
        return Msg.fail();
    }




    /**
     * 更新教师的密码
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "resetteacherpsw")
    @ResponseBody
    public Msg resetTeacherPsw( @RequestBody Map<String, Object> map,HttpServletResponse response, HttpServletRequest request) throws Exception {


        System.out.println("resetTeacherPsw 函数执行了");
        int id=Integer.parseInt(map.getOrDefault("id","1000").toString());
        String psw=map.getOrDefault("psw","123").toString();
        System.out.println("id:"+id);
        System.out.println("psw:"+psw);
        User user = new User();
        user.setId(id);
        user.setPsw(psw);
        user.setRole(1);
        int res=userService.resetPsw(user.getId(),user.getPsw(),user.getRole());
        if (res ==1) {
            System.out.println("修改成功");
            return Msg.success();
        }
        System.out.println("修改失败");
        return Msg.fail();
    }









    /**
     * 学生查询已经选的课的信息
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "getstudentofcourse")
    @ResponseBody
    public Msg getStudentOfCourse(@RequestBody Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("getStudentOfCourse 函数执行了");
        System.out.println(map);
        int currentPage = (Integer) map.getOrDefault("currentPage", 1);
        int pageSize = (Integer) map.getOrDefault("pageSize", 3);
        int tid = (Integer) map.getOrDefault("id", 0);
        System.out.println("得到教师tid:"+tid);
        int cid = (Integer) map.getOrDefault("cid", 0);
        System.out.println("得到课程cid:"+cid);

        //1.将课程page分页
        PageHelper.startPage(currentPage, pageSize);
        //2.查询所有的课程,当然该课程必须需要这个学生还没有选中或者上过课
        List<Coursestudent>  coursestudents= coursestudentService.getCourseStudentListByTidCid(tid,cid);
        //3.返回结果
        PageInfo page = new PageInfo(coursestudents, 3);
        System.out.println(page);

        return Msg.success().add("PageInfo", page);
    }






    /**
     * 学号为sid的学生退编号为cid的tid老师讲授的课程
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "markcoursestudent")
    @ResponseBody
    public Msg markCourseStudent(@RequestBody Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("exitCourse 函数执行了");
        System.out.println(map);
        int currentPage = (Integer) map.getOrDefault("currentPage", 1);
        int pageSize = (Integer) map.getOrDefault("pageSize", 3);
        int sid = Integer.parseInt(map.getOrDefault("sid", "0").toString());
        int cid = Integer.parseInt(map.getOrDefault("cid", "0").toString());
        int tid = Integer.parseInt(map.getOrDefault("tid", "0").toString());
        int score=Integer.parseInt(map.getOrDefault("score", "0").toString());
        Coursestudent coursestudent = new Coursestudent();
        coursestudent.setType(1);
        coursestudent.setTid(tid);
        coursestudent.setSid(sid);
        coursestudent.setCid(cid);
        coursestudent.setScore((float)score);
        int res = 0;
        try {
            res = coursestudentService.markCourseStudent(coursestudent);
            if (res == 1) {
                System.out.println("打分成功");
                return Msg.success();
            }
            return Msg.fail();
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }








}
