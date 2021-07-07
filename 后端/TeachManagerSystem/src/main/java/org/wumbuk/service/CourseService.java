package org.wumbuk.service;

import org.wumbuk.entity.Course;

import java.util.List;

public interface CourseService {


    /**
     * 返回所有的课程
     * @return
     */
    List<Course> getAllCourse();


    /**
     * 通过关键字进行模糊查询
     * @param keyWord
     * @return
     */
    List<Course> getCourseFuzzy(String keyWord);

    /**
     * 添加课程信息，但是值得注意的是，在老师-课程的表也要继续地进行维护
     * @param course
     * @return
     */
    int addCourse(Course course);

    int editCourse(Course course);

    //
    List<Course> getAllCourseByStudent(int sid);

    //通过学生学号和课程名模糊查询课程
    List<Course> checkCourseFuzzyStudent(int sid, String keyWord);

    /**
     * 通过学号查询已经选的选修课
     * @param id
     * @return
     */
    List<Course> getChosenCourseByStudent(int id);

    List<Course> checkCourseNameChosenFuzzy(int sid, String keyWord);

    /**
     * 查询已经出分的课程
     * @param id
     * @return
     */
    List<Course> getFinishedCourseByStudent(int id);

    /**
     * 通过keyWord 查询用户为sid的模糊查询
     * @param sid
     * @param keyWord
     * @return
     */
    List<Course> checkCoursenameFinishedfuzzy(int sid, String keyWord);

    //根据教师的tid查询该教师所教授的所有的课程的信息
    List<Course> getAllCourseByTeacher(int id);


    /**
     * 教师根据教师编号和关键词查询课程名
     * @param tid
     * @param keyWord
     * @return
     */
    List<Course> checkCourseFuzzyTeacher(int tid, String keyWord);
}
