package org.wumbuk.service;


import org.wumbuk.entity.Teacher;

import java.util.List;

public interface TeacherCourseService {


    /**
     * 根据课程号和教师编号删除课程
     * @param cid
     * @param tid
     * @return
     */
    int deleteByCidAndTid(Integer cid,Integer tid);

}
