package org.wumbuk.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wumbuk.entity.TeachrCourse;
@Mapper
@Repository
public interface TeachrCourseMapper {
    int insert(TeachrCourse record);

    int insertSelective(TeachrCourse record);


    @Delete("DELETE FROM `teachr-course` WHERE cId=#{cid} AND tId=#{tid}")
    int deleteByCidAndTid(Integer cid, Integer tid);
}