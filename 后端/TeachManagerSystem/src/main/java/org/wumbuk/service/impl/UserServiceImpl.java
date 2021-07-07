package org.wumbuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wumbuk.dao.AdminMapper;
import org.wumbuk.dao.StudentMapper;
import org.wumbuk.dao.TeacherMapper;
import org.wumbuk.dao.UserMapper;
import org.wumbuk.entity.Admin;
import org.wumbuk.entity.Student;
import org.wumbuk.entity.Teacher;
import org.wumbuk.entity.User;
import org.wumbuk.service.UserService;
import org.wumbuk.utils.SecurePwd;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TeacherMapper teacherMapper;




    @Override
    public int login(User user) {
        System.out.println("参数user:"+user);
        User user1 = userMapper.selectByIdAndRole(user.getId(), user.getRole());

        System.out.println(user1);
        try {
            if(null!=user1&& SecurePwd.encrypt(user.getPsw()).equals(user1.getPsw())){
                System.out.println("用户登录成功");
                return user1.getId();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("登录业务逻辑层出现问题");
            return  0;
        }
        return 0;
    }

    @Override
    public String getPhoneByIdAndRole(User user) {
        User user1 = userMapper.selectByIdAndRole(user.getId(), user.getRole());
        try {
            if(null!=user1 && null!=user1.getPhone()){
                System.out.println("查询电话号成功");
                System.out.println("电话号为："+user1.getPhone());
                return user1.getPhone();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getPhoneByIdAndRole出现问题");
            return  null;
        }

        return null;
    }

    @Override
    public User getInformation(String uid, int i) {
        return userMapper.selectByIdAndRoleDetail(uid,i);
    }

    @Override
    public List<User> getallUsersExceptAdmin(String id) {

        List<User> res=userMapper.getallUsersExceptAdmin(id);
        System.out.println("Service中的userList:"+res);
        return res;
    }

    @Override
    public List<User> checkUserFuzzyExceptAdmin(String keyWord, String id) {
        return userMapper.checkUserFuzzyExceptAdmin(keyWord,id);
    }

    @Override
    public int resetPsw(int id, String psw,int role) throws Exception {
        User user = new User();
        user.setId(id);
        user.setRole(role);
        user.setPsw(SecurePwd.encrypt(psw));
        System.out.println("加密后的密码为"+user.getPsw());
        System.out.println("user:"+user);
        int res=userMapper.updateByIdAndRole(user.getId(),user.getPsw(),user.getRole());
        return  res;
    }


    @Override
    public int updateAvatar(User user) {
        if(user.getRole()==0){
            Admin admin = new Admin();
            admin.setAid(user.getId());
            admin.setAvatar(user.getAvatar());
            System.out.println("admin:"+admin);
            return adminMapper.updateByPrimaryKeySelective(admin);
        }else if(user.getRole()==1){

            Teacher teacher=new Teacher();
            teacher.setAvatar(user.getAvatar());
            teacher.setTid(user.getId());;
            return teacherMapper.updateByPrimaryKeySelective(teacher);
        }else if(user.getRole()==2){
            Student student=new Student();
            student.setSid(user.getId());
            student.setAvatar(user.getAvatar());
            return studentMapper.updateByPrimaryKeySelective(student);
        }

        return 0;
    }

}
