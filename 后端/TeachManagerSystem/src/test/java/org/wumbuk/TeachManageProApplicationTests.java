package org.wumbuk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wumbuk.dao.AdminMapper;
import org.wumbuk.dao.StudentMapper;
import org.wumbuk.dao.TeacherMapper;
import org.wumbuk.dao.UserMapper;
import org.wumbuk.entity.Admin;
import org.wumbuk.entity.Student;
import org.wumbuk.entity.Teacher;
import org.wumbuk.entity.User;
import org.wumbuk.utils.SecurePwd;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TeachManageProApplicationTests {

    @Autowired
    AdminMapper adminMapper;
    
    @Autowired
    StudentMapper studentMapper;
    
    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    UserMapper userMapper;
    
    
    @Test
    void contextLoads() throws Exception {
        System.out.println(SecurePwd.encrypt("123"));
    }


    @Test
    void testList(){
        List<User> userList=new ArrayList<User>();
        User user1 = new User();
        user1.setId(1);
        userList.add(user1);
        User user2 = new User();
        user2.setId(2);
        userList.add(user2);
        System.out.println(userList);
    }


    @Test
    void testSecure() throws Exception {
        System.out.println(SecurePwd.encrypt("1234"));
    }



    @Test
    void restore() throws Exception {

        List<Admin> admins=adminMapper.selectAll();
        System.out.println(admins);
        for (Admin admin : admins) {
            Integer id=admin.getAid();
            String psw=SecurePwd.encrypt(Integer.toString(admin.getAid()));
            String phone="15166675416";
            userMapper.restore(id,psw,phone,0);
        }

        List<Teacher> teachers=teacherMapper.selectAll();
        for (Teacher teacher : teachers) {
            Integer id=teacher.getTid();
            String psw=SecurePwd.encrypt(Integer.toString(teacher.getTid()));
            String phone="15166675416";
            userMapper.restore(id,psw,phone,1);

        }


        List<Student> students=studentMapper.selectAllStudent();
        for (Student student : students) {
            Integer id=student.getSid();
            String psw=SecurePwd.encrypt(Integer.toString(student.getSid()));
            String phone="15166675416";
            userMapper.restore(id,psw,phone,2);
        }



        
    }
}
