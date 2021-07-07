package org.wumbuk.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wumbuk.entity.*;
import org.wumbuk.service.*;
import org.wumbuk.utils.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class AdminController {

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
        String uid=null;
        System.out.println("getUserInformation 函数执行了");
        HttpSession session = request.getSession(false);
        if (null == session) {
            System.out.println("得到session是空的");
            System.out.println("用户状态非法");
            msg.setCode(1);
            msg.setMsg("用户状态非法");
            return msg;
        }
        session.setMaxInactiveInterval(60*60*30);
        Cookie[] cookies = request.getCookies();//根据请求数据，找到cookie数组
        boolean uidflag = false;
        boolean flag=false;
        for (Cookie cookie : cookies) {
            if (cookie.getValue().equals("logoff")){
                System.out.println("已登出");
                cookie.setValue(null);
                flag=true;
            }
            if (cookie.getName().equals("uid")) {
                uidflag=true;
                uid=cookie.getValue();
                cookie.setMaxAge(60*60*30);
                response.addCookie(cookie);
            }
        }
        if (flag){
            System.out.println("检测到已经登出");
            msg.setCode(1);
            return msg;
        }
        if(uidflag){
            System.out.println("Controller得到的uid为"+uid);
            User userInfo=userService.getInformation(uid,0);
            System.out.println("得到的用户数据为"+userInfo);
            msg.setCode(0);
            msg.add("user",userInfo);
            return msg;
        }else {
            System.out.println("得到cookie是空的");
            System.out.println("用户状态非法");
            msg.setCode(1);
            msg.setMsg("用户状态非法");
            return msg;
        }
    }





    /**
     * 查询所有的课程信息
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "getallcourse")
    @ResponseBody
    public Msg getAllCourse(@RequestBody Map<String,Object> map, HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("getAllCourse 函数执行了");
        System.out.println(map);
        int  currentPage=(Integer) map.getOrDefault("currentPage",1);
        int  pageSize=(Integer) map.getOrDefault("pageSize",3);
//        System.out.println(pageSize);

        //1.将课程page分页
        PageHelper.startPage(currentPage, pageSize);
        //2.查询所有的课程
        List<Course> courseList=courseService.getAllCourse();
        //3.返回结果
        PageInfo page=new PageInfo(courseList,3);
        System.out.println(page);

        return Msg.success().add("PageInfo",page);

    }







    /**
     * 查询所有的课程信息
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "test")
    @ResponseBody
    public Msg Test(@RequestBody Map<String,Object> map, HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("getAllCourse 函数执行了");
        System.out.println(map);
        int  currentPage=(Integer) map.getOrDefault("currentPage",1);
        int  pageSize=(Integer) map.getOrDefault("pageSize",3);
//        System.out.println(pageSize);

        //1.将课程page分页
//        PageHelper.startPage(currentPage, pageSize);
        //2.查询所有的课程
        List<Course> courseList=courseService.getAllCourse();
        System.out.println(courseList);
        //3.返回结果
//        PageInfo page=new PageInfo(courseList,3);
//        System.out.println(page);
        return Msg.success().add("courseList",courseList);

    }







    /**
     * 根据课程号和教师编号删除课程
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "deletecoursebycidandtid")
    @ResponseBody
    public Msg DeleteCourseByCidAndTid(@RequestBody Map<String,Object> map, HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("DeleteCourseByCidAndTid 函数执行了");
        System.out.println(map);
        int  cid=(Integer) map.getOrDefault("cid",1);
        int  tid=(Integer) map.getOrDefault("tid",3);
        int res=teacherCourseService.deleteByCidAndTid(cid,tid);
        if (res!=1){
            return Msg.fail();
        }
        return Msg.success();
    }



    /**
     * 模糊查询课程信息
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "selectcoursefuzzy")
    @ResponseBody
    public Msg selectCourseFuzzy(@RequestBody Map<String,Object> map, HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("selectCourseFuzzy 函数执行了");
        System.out.println(map);
        int  currentPage=(Integer) map.getOrDefault("currentPage",1);
        int  pageSize=(Integer) map.getOrDefault("pageSize",3);
        String keyWord=map.getOrDefault("keyWord","nothing").toString();
        //1.将课程page分页
        PageHelper.startPage(currentPage, pageSize);
        //2.查询所有模糊查询的课程
        List<Course> courseList=courseService.getCourseFuzzy(keyWord);
        //3.返回结果
        PageInfo page=new PageInfo(courseList,3);
        System.out.println(page);
        return Msg.success().add("PageInfo",page);
    }




    /**
     * 查询所有的老师的信息
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, value = "getteachers")
    @ResponseBody
    public Msg getTeachers(HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("getTeachers 函数执行了");


        List<Teacher> teacherList=teacherService.getAllTeachers();

        return Msg.success().add("teacherList",teacherList);
    }






    /**
     * 添加课程的信息
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "addcourse")
    @ResponseBody
    public Msg addCourse(@RequestBody Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws Exception {
        System.out.println("AddCourse 函数执行了");
        Course course = JsonUtilList.mapToObject(map, Course.class, "course");
        System.out.println("得到转化类"+course);
        int res = courseService.addCourse(course);
        if (res == 1) {
            System.out.println("添加课程成功");
            return Msg.success();
        }
        System.out.println("添加课程失败");
        return Msg.fail();

    }



    /**
     * 修改课程的信息
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "editcourse")
    @ResponseBody
    public Msg editCourse( @RequestBody Map<String, Object> map,HttpServletResponse response, HttpServletRequest request) throws Exception {


        System.out.println("editCourse 函数执行了");
        Course course = JsonUtilList.mapToObject(map, Course.class, "course");
        System.out.println("得到转化类"+course);
        int res = courseService.editCourse(course);
        if (res == 1) {
            System.out.println("修改课程成功");
            return Msg.success();
        }
        System.out.println("修改课程失败");
        return Msg.fail();


    }






    /**
     * 获取所有的学生的信息
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "getallstudents")
    @ResponseBody
    public Msg getAllStudents(@RequestBody Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws Exception {

        System.out.println("getAllStudents 函数执行了");
        System.out.println(map);
        int  currentPage=(Integer) map.getOrDefault("currentPage",1);
        int  pageSize=(Integer) map.getOrDefault("pageSize",3);

        //1.将课程page分页
        PageHelper.startPage(currentPage, pageSize);
        //2.查询所有的课程
        List<Student> studentList=studentService.getAllStudent();
        //3.返回结果
        PageInfo page=new PageInfo(studentList,3);

        System.out.println(page);

        return Msg.success().add("PageInfo",page);

    }



    /**
     * 模糊查询学生信息，通过学生姓名或者学生的学号
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "selectstudentfuzzy")
    @ResponseBody
    public Msg selectStudentFuzzy(@RequestBody Map<String,Object> map, HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("selectStudentFuzzy 函数执行了");
        System.out.println(map);
        int  currentPage=(Integer) map.getOrDefault("currentPage",1);
        int  pageSize=(Integer) map.getOrDefault("pageSize",3);
        String keyWord=map.getOrDefault("keyWord","nothing").toString();
        //1.将课程page分页
        PageHelper.startPage(currentPage, pageSize);
        //2.查询所有模糊查询的课程
        List<Student> studentList=studentService.getStudentFuzzy(keyWord);
        //3.返回结果
        PageInfo page=new PageInfo(studentList,3);
        System.out.println(page);
        return Msg.success().add("PageInfo",page);
    }



    /**
     * 根据学生id删除学生
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "deletestudentbysid")
    @ResponseBody
    public Msg DeleteStudentBySid(@RequestBody Map<String,Object> map, HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("DeleteStudentBySid 函数执行了");
        System.out.println(map);
        int  sid=(Integer) map.getOrDefault("sid","9999999999");
        int res=studentService.deleteStudentBySid(sid);
        if (res!=1){
            return Msg.fail();
        }
        return Msg.success();
    }



    /**
     * 查询所有院系的信息
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, value = "getalldapatures")
    @ResponseBody
    public Msg getAlldapatures(HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("getAlldapatures 函数执行了");


        List<Depature> depatureList=depatureService.getAlldapatures();

        return Msg.success().add("depatureList",depatureList);
    }




    /**
     * 添加学生的信息
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "addstudent")
    @ResponseBody
    public Msg addStudent( @RequestBody Map<String, Object> map,HttpServletResponse response, HttpServletRequest request) throws Exception {
        System.out.println("addStudent 函数执行了");
        Student student = JsonUtilList.mapToObject(map, Student.class, "student");
        System.out.println("得到转化类"+student);
        int res = studentService.addStudent(student);
        if (res == 1) {
            System.out.println("添加学生成功");
            return Msg.success();
        }
        System.out.println("添加学生失败");
        return Msg.fail();

    }






    /**
     * 修改学生的信息
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "editstudent")
    @ResponseBody
    public Msg editStudent( @RequestBody Map<String, Object> map,HttpServletResponse response, HttpServletRequest request) throws Exception {


        System.out.println("editStudent 函数执行了");
        Student student = JsonUtilList.mapToObject(map, Student.class, "student");
        System.out.println("得到转化类"+student);
        int res = studentService.editStudent(student);
        if (res == 1) {
            System.out.println("修改学生成功");
            return Msg.success();
        }
        System.out.println("修改学生失败");
        return Msg.fail();


    }




    /**
     * 获取所有的老师的信息
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "getallteacherspage")
    @ResponseBody
    public Msg getAllTeachersPage(@RequestBody Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws Exception {

        System.out.println("getAllTeachersPage 函数执行了");
        System.out.println(map);
        int  currentPage=(Integer) map.getOrDefault("currentPage",1);
        int  pageSize=(Integer) map.getOrDefault("pageSize",3);

        //1.将课程page分页
        PageHelper.startPage(currentPage, pageSize);
        //2.查询所有的课程
        List<Teacher> teacherList=teacherService.getAllTeachers();
        //3.返回结果
        PageInfo page=new PageInfo(teacherList,3);

        System.out.println(page);

        return Msg.success().add("PageInfo",page);

    }




    /**
     * 模糊查询学生信息，通过学生姓名或者学生的学号
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "selectteacherfuzzy")
    @ResponseBody
    public Msg selectTeacherfuzzy(@RequestBody Map<String,Object> map, HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("selectTeacherfuzzy 函数执行了");
        System.out.println(map);
        int  currentPage=(Integer) map.getOrDefault("currentPage",1);
        int  pageSize=(Integer) map.getOrDefault("pageSize",3);
        String keyWord=map.getOrDefault("keyWord","nothing").toString();
        //1.将课程page分页
        PageHelper.startPage(currentPage, pageSize);
        //2.查询所有模糊查询的课程
        List<Teacher> teacherList=teacherService.getTeacherFuzzy(keyWord);
        //3.返回结果
        PageInfo page=new PageInfo(teacherList,3);
        System.out.println(page);
        return Msg.success().add("PageInfo",page);
    }



    /**
     * 根据教师id删除教师
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "deleteteacherbytid")
    @ResponseBody
    public Msg deleteTeacherByTid(@RequestBody Map<String,Object> map, HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("deleteTeacherByTid 函数执行了");
        System.out.println(map);
        int  tid=(Integer) map.getOrDefault("tid","9999999999");
        int res=teacherService.deleteTeacherByTid(tid);
        if (res!=1){
            return Msg.fail();
        }
        return Msg.success();
    }




    /**
     * 添加学生的信息
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "addteacher")
    @ResponseBody
    public Msg addTeacher( @RequestBody Map<String, Object> map,HttpServletResponse response, HttpServletRequest request) throws Exception {
        System.out.println("addTeacher 函数执行了");
        Teacher teacher = JsonUtilList.mapToObject(map, Teacher.class, "teacher");
        System.out.println("得到转化类"+teacher);
        int res = teacherService.addTeacher(teacher);
        if (res == 1) {
            System.out.println("添加学生成功");
            return Msg.success();
        }
        System.out.println("添加学生失败");
        return Msg.fail();
    }



    /**
     * 修改老师的信息
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "editteacher")
    @ResponseBody
    public Msg editTeacher( @RequestBody Map<String, Object> map,HttpServletResponse response, HttpServletRequest request) throws Exception {


        System.out.println("editTeacher 函数执行了");
        Teacher teacher = JsonUtilList.mapToObject(map, Teacher.class, "teacher");
        System.out.println("得到转化类"+teacher);
        int res = teacherService.editTeacher(teacher);
        if (res == 1) {
            System.out.println("修改教师成功");
            return Msg.success();
        }
        System.out.println("修改教师失败");
        return Msg.fail();
    }




    /**
     * 获取所有的用户的信息
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "getallusers")
    @ResponseBody
    public Msg getallUsers(@RequestBody Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws Exception {

        System.out.println("getallUsers 函数执行了");
        System.out.println(map);
        int  currentPage=(Integer) map.getOrDefault("currentPage",1);
        int  pageSize=(Integer) map.getOrDefault("pageSize",3);
        //注意应该调用toString()的方法，而不是直接类型转换
        String id=map.getOrDefault("id","1000").toString();

        //1.将课程page分页
        PageHelper.startPage(currentPage, pageSize);
        //2.查询所有的课程
        List<User>  userList=userService.getallUsersExceptAdmin(id);
        for (User user : userList) {
            user.setPsw(SecurePwd.decrypt(user.getPsw()));
        }
        System.out.println("Controller中userList:"+userList);
        //3.返回结果
        PageInfo page=new PageInfo(userList,3);
        System.out.println(page);
        return Msg.success().add("PageInfo",page);

    }




    /**
     * 模糊查询所有用户信息，通过学生姓名或者学生的学号（除了本管理员）
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "checkuserfuzzy")
    @ResponseBody
    public Msg checkUserFuzzy(@RequestBody Map<String,Object> map, HttpServletResponse response, HttpServletRequest request) throws Exception {
        System.out.println("checkUserFuzzy 函数执行了");
        System.out.println(map);
        int  currentPage=(Integer) map.getOrDefault("currentPage",1);
        int  pageSize=(Integer) map.getOrDefault("pageSize",3);
        String keyWord=map.getOrDefault("keyWord","nothing").toString();
        String id=map.getOrDefault("id","1000").toString();
        //1.将课程page分页
        PageHelper.startPage(currentPage, pageSize);
        //2.查询所有模糊查询的课程
        List<User> userList=userService.checkUserFuzzyExceptAdmin(keyWord,id);
        for (User user : userList) {
            user.setPsw(SecurePwd.decrypt(user.getPsw()));
        }
        //3.返回结果
        PageInfo page=new PageInfo(userList,3);
        System.out.println(page);
        return Msg.success().add("PageInfo",page);
    }






    /**
     * 重置用户的密码
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "resetpsw")
    @ResponseBody
    public Msg resetPsw( @RequestBody Map<String, Object> map,HttpServletResponse response, HttpServletRequest request) throws Exception {


        System.out.println("resetPsw 函数执行了");
        int id=(Integer) map.getOrDefault("id","1000");
        String psw=map.getOrDefault("psw","123").toString();
        int role=(Integer) map.getOrDefault("role","1");
        System.out.println("id:"+id);
        System.out.println("psw:"+psw);

        int res = userService.resetPsw(id,psw,role);
        if (res == 1) {
            System.out.println("重置密码成功");
            return Msg.success();
        }
        System.out.println("重置密码失败");
        return Msg.fail();
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
        user.setRole(0);
        int res=userService.login(user);
        if (res !=0) {
            System.out.println("校验成功");
            return Msg.success();
        }
        System.out.println("校验失败");
        return Msg.fail();
    }





    /**
     * 更新管理员的密码
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "resetadminpsw")
    @ResponseBody
    public Msg resetAdminPsw( @RequestBody Map<String, Object> map,HttpServletResponse response, HttpServletRequest request) throws Exception {


        System.out.println("resetAdminPsw 函数执行了");
        int id=Integer.parseInt(map.getOrDefault("id","1000").toString());
        String psw=map.getOrDefault("psw","123").toString();
        System.out.println("id:"+id);
        System.out.println("psw:"+psw);
        User user = new User();
        user.setId(id);
        user.setPsw(psw);
        user.setRole(0);
        int res=userService.resetPsw(user.getId(),user.getPsw(),user.getRole());
        if (res ==1) {
            System.out.println("修改成功");
            return Msg.success();
        }
        System.out.println("修改失败");
        return Msg.fail();
    }










}
