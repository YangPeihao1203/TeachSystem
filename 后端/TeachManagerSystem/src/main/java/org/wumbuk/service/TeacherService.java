package org.wumbuk.service;

import org.wumbuk.entity.Teacher;

import java.util.List;

public interface TeacherService {


    List<Teacher> getAllTeachers();

    /**
     * 模糊查询教师的信息
     * @param keyWord
     * @return
     */
    List<Teacher> getTeacherFuzzy(String keyWord);

    /**
     * 根据教师的编号删除教师
     * @param tid
     * @return
     */
    int deleteTeacherByTid(int tid);

    /**
     * 增加教师信息
     * @param teacher
     * @return
     */
    int addTeacher(Teacher teacher) throws Exception;

    /**
     * 修改教师信息
     * @param teacher
     * @return
     */
    int editTeacher(Teacher teacher);
}
