package org.wumbuk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wumbuk.entity.Teacher;

import java.util.List;

@Mapper
@Repository
public interface TeacherMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);


    @Select("select * from teacher")
    List<Teacher> getAllTeacher();


    /**
     * 模糊查询教师的基本信息
     * @param keyWord
     * @return
     */
    List<Teacher> selectTeacherFuzzy(String keyWord);


    @Select("select * from teacher")
    List<Teacher> selectAll();
}