package org.wumbuk.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wumbuk.dao.TeachrCourseMapper;
import org.wumbuk.service.TeacherCourseService;

@Service("teacherCourseService")
public class TeacherCourseServiceImpl implements TeacherCourseService {

    @Autowired
    TeachrCourseMapper teacherCourseMapper;

    @Override
    public int deleteByCidAndTid(Integer cid, Integer tid) {
        System.out.println("deleteByCidAndTid()");
        return teacherCourseMapper.deleteByCidAndTid(cid,tid);
    }
}
