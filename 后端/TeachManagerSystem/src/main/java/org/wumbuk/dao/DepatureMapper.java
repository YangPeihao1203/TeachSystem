package org.wumbuk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wumbuk.entity.Depature;

import java.util.List;

@Mapper
@Repository
public interface DepatureMapper {
    int deleteByPrimaryKey(Integer did);

    int insert(Depature record);

    int insertSelective(Depature record);

    Depature selectByPrimaryKey(Integer did);

    int updateByPrimaryKeySelective(Depature record);

    int updateByPrimaryKey(Depature record);


    @Select("select * from depature")
    List<Depature> selectAll();
}