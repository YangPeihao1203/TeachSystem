package org.wumbuk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wumbuk.entity.Detail;
@Mapper
@Repository
public interface DetailMapper {
    int insert(Detail record);

    int insertSelective(Detail record);
}