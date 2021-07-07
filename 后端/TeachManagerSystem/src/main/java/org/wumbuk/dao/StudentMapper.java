package org.wumbuk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wumbuk.entity.Student;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    @Select("select * from student ")
    List<Student> selectAllStudent();

    List<Student> getStudentFuzzy(String keyWord);
}