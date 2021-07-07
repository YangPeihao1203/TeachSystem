package org.wumbuk.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wumbuk.dao.CoursestudentMapper;
import org.wumbuk.entity.Coursestudent;
import org.wumbuk.service.CoursestudentService;

import java.util.List;


@Service("coursestudentService")
public class CoursestudentServiceImpl implements CoursestudentService {

    @Autowired
    CoursestudentMapper coursestudentMapper;

    @Override
    public int chooseCourse(int sid, int cid, int tid) {
        Coursestudent coursestudent = new Coursestudent();
        coursestudent.setCid(cid);
        coursestudent.setSid(sid);
        coursestudent.setTid(tid);
        coursestudent.setType(0);
        System.out.println("待插入课程："+coursestudent);
        return coursestudentMapper.insertSelective(coursestudent);
    }

    @Override
    public int exitCourse(int sid, int cid, int tid) {
        return coursestudentMapper.deleteBySidCidTid(sid,  cid,tid);
    }

    @Override
    public List<Coursestudent> getCourseStudentListByTidCid(int tid, int cid) {
        return coursestudentMapper.selectCourseStudentByTidAndCid(tid,cid);
    }

    @Override
    public int markCourseStudent(Coursestudent coursestudent) {
        System.out.println("插入的coursestudent"+coursestudent);
        return coursestudentMapper.markCourseStu(coursestudent.getCid(),coursestudent.getTid(),coursestudent.getScore());
    }
}
