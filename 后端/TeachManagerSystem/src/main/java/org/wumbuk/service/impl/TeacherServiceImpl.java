package org.wumbuk.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wumbuk.dao.TeacherMapper;
import org.wumbuk.dao.UserMapper;
import org.wumbuk.entity.Teacher;
import org.wumbuk.entity.User;
import org.wumbuk.service.TeacherService;
import org.wumbuk.service.UserService;
import org.wumbuk.utils.SecurePwd;

import java.util.List;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherMapper.getAllTeacher();
    }

    @Override
    public List<Teacher> getTeacherFuzzy(String keyWord) {
        return teacherMapper.selectTeacherFuzzy(keyWord);
    }


    @Override
    public int deleteTeacherByTid(int tid) {
        int res1= teacherMapper.deleteByPrimaryKey(tid);
        int res2=userMapper.deleteByIdAndRole(tid,1);
        return res1*res2;
    }

    @Override
    public int addTeacher(Teacher teacher) throws Exception {
        int res1= teacherMapper.insertSelective(teacher);
        User user = new User();
        user.setId(teacher.getTid());
        user.setPsw(SecurePwd.encrypt(Integer.toString(teacher.getTid())));
        user.setRole(1);
        user.setPhone(teacher.getPhone());
        System.out.println("要插入的user:"+user);
        int res2 = userMapper.insertSelective(user);
        return res1*res2;


    }

    @Override
    public int editTeacher(Teacher teacher) {


        int res1= teacherMapper.updateByPrimaryKeySelective(teacher);
        int res2=userMapper.updatePhone(teacher.getTid(),1,teacher.getPhone());
        return res1*res2;

    }

}
