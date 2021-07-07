package org.wumbuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wumbuk.dao.StudentMapper;
import org.wumbuk.dao.UserMapper;
import org.wumbuk.entity.Student;
import org.wumbuk.entity.User;
import org.wumbuk.service.StudentService;
import org.wumbuk.utils.SecurePwd;

import java.util.List;


@Service("studentService")
public class StudentServiceImpl implements StudentService {


    @Autowired
    StudentMapper studentMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<Student> getAllStudent() {
        return studentMapper.selectAllStudent();
    }



    @Override
    public List<Student> getStudentFuzzy(String keyWord) {
        System.out.println("getStudentFuzzy...");

        return studentMapper.getStudentFuzzy(keyWord);
    }

    @Override
    public int deleteStudentBySid(int sid) {
        int res1=studentMapper.deleteByPrimaryKey(sid);
        int res2=userMapper.deleteByIdAndRole(sid,2);
        return res1*res2;

    }


    @Override
    public int addStudent(Student student) throws Exception {
        int res1=studentMapper.insert(student);
        User user = new User();
        user.setId(student.getSid());
        user.setPsw(SecurePwd.encrypt(Integer.toString(student.getSid())));
        user.setRole(2);
        user.setPhone(student.getPhone());
        int res2=userMapper.insert(user);
        return res1*res2;
    }

    @Override
    public int editStudent(Student student) {
        int res1= studentMapper.updateByPrimaryKeySelective(student);
        int res2=userMapper.updatePhone(student.getSid(),2,student.getPhone());



        return res1*res2;
    }



}
