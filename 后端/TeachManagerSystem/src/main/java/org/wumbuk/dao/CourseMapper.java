package org.wumbuk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.wumbuk.entity.Course;

import java.util.List;

@Mapper
@Repository
public interface CourseMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);


    List<Course> selectAllCourse();

    /**
     * 通过纯字符串数实现模糊查询，之要比较字符是否包含就行
     * @param keyWord
     * @return
     */
    List<Course> getCourseFuzzy(String keyWord);


    List<Course> getAllCourseByStudentSid(int sid);


    /**
     * 通过学生Id和课程名查询课程
     * @param sid
     * @param keyWord
     * @return
     */
    List<Course> checkCourseFuzzyStudent( @Param("sid") int sid,@Param("keyWord") String keyWord);




    List<Course> getChosenCourseByStudentSid(int sid);

    List<Course> checkCourseChosenFuzzyStudent(@Param("sid") int sid, @Param("keyWord") String keyWord);


    List<Course> getFinishedCourseByStudentSid(int sid);

    List<Course> checkCoursenameFinishedfuzzy(@Param("sid") int sid, @Param("keyWord")String keyWord);

    List<Course> getAllCourseByTeacher(int tid);

    List<Course> checkCourseFuzzyTeacher(@Param("tid")int tid, @Param("keyWord")String keyWord);


    /**
     * 通过数字实现模糊查询，只要比较数字是否一样就行
     * @param key
     * @return
     */
//    List<Course> getCourseFuzzyInteger(Integer key);

}