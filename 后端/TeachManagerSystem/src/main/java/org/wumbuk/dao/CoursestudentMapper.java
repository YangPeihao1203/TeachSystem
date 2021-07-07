package org.wumbuk.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.wumbuk.entity.Coursestudent;

import java.util.List;


@Mapper
@Repository
public interface CoursestudentMapper {
    int insert(Coursestudent record);

    int insertSelective(Coursestudent record);

    @Delete("delete from `coursestudent` where sid=#{sid} and cid=#{cid} and tid=#{tid}" )
    int deleteBySidCidTid(int sid, int cid, int tid);

    List<Coursestudent> selectCourseStudentByTidAndCid(int tid, int cid);

    @Update("update `coursestudent` set score=#{score},type=1 where cid=#{cid} and tid=#{tid}")
    int markCourseStu(Integer cid, Integer tid, Float score);
}