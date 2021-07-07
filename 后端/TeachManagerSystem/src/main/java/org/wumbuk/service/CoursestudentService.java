package org.wumbuk.service;


import org.wumbuk.entity.Coursestudent;

import java.util.List;

public interface CoursestudentService {

    /**
     * sid的学生选编号为cid的课，同时该课是编号为tid的教师讲授的
     * @param sid
     * @param cid
     * @param tid
     * @return
     */
    int chooseCourse(int sid, int cid, int tid);

    int exitCourse(int sid, int cid, int tid);

    List<Coursestudent> getCourseStudentListByTidCid(int tid, int cid);

    int markCourseStudent(Coursestudent coursestudent);
}
