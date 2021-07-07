package org.wumbuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wumbuk.dao.CourseMapper;
import org.wumbuk.dao.TeachrCourseMapper;
import org.wumbuk.dao.UserMapper;
import org.wumbuk.entity.Course;
import org.wumbuk.entity.TeachrCourse;
import org.wumbuk.service.CourseService;
import org.wumbuk.utils.StrUtil;

import java.util.List;

@Service("courseService")
public class CourseServiceImpl implements CourseService {


    @Autowired
    CourseMapper courseMapper;

    @Autowired
    TeachrCourseMapper teachrCourseMapper;

    @Override
    public List<Course> getAllCourse() {

        System.out.println("getAllCourse()开始执行");
        return courseMapper.selectAllCourse();

    }

    @Override
    public List<Course> getCourseFuzzy(String keyWord) {
        System.out.println("getCourseFuzzy()执行");
        //1.判断keyWord完全是字符串
      return courseMapper.getCourseFuzzy(keyWord);

    }

    @Override
    public int addCourse(Course course) {
        TeachrCourse teachrCourse = new TeachrCourse();
        teachrCourse.setCid(course.getCid());
        teachrCourse.setTid(course.getTid());
        int res1=courseMapper.insert(course);
        System.out.println("res1:"+res1);
        int res2=teachrCourseMapper.insert(teachrCourse);
        System.out.println("res2"+res2);

         return res1*res2;
    }

    @Override
    public int editCourse(Course course) {
        return courseMapper.updateByPrimaryKeySelective(course);
    }

    //获得该学号为sid学生可以选的课的列表
    @Override
    public List<Course> getAllCourseByStudent(int sid) {
        List<Course> courseList=courseMapper.getAllCourseByStudentSid(sid);
        return courseList;
    }

    @Override
    public List<Course> checkCourseFuzzyStudent(int sid, String keyWord) {
        return courseMapper.checkCourseFuzzyStudent(sid,keyWord);
    }

    @Override
    public List<Course> getChosenCourseByStudent(int sid) {
        List<Course> courseList=courseMapper.getChosenCourseByStudentSid(sid);
        System.out.println("courseList:"+courseList);
        return courseList;
    }

    @Override
    public List<Course> checkCourseNameChosenFuzzy(int sid, String keyWord) {
        return courseMapper.checkCourseChosenFuzzyStudent(sid,keyWord);
    }

    @Override
    public List<Course> getFinishedCourseByStudent(int sid) {
        List<Course> courseList=courseMapper.getFinishedCourseByStudentSid(sid);
        System.out.println("courseList:"+courseList);
        return courseList;

    }

    @Override
    public List<Course> checkCoursenameFinishedfuzzy(int sid, String keyWord) {
        return courseMapper.checkCoursenameFinishedfuzzy(sid,keyWord);
    }

    @Override
    public List<Course> getAllCourseByTeacher(int tid) {
        return courseMapper.getAllCourseByTeacher(tid);
    }

    @Override
    public List<Course> checkCourseFuzzyTeacher(int tid, String keyWord) {
        return courseMapper.checkCourseFuzzyTeacher(tid,keyWord);
    }


}
