package org.wumbuk.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.wumbuk.entity.User;

import java.util.List;


@Mapper
@Repository
public interface UserMapper {

    int insert(User record);

    int insertSelective(User record);


    /**
     * 通过id和role去查询用户信息
     * @param id
     * @param role
     * @return
     */
    @Select("select * from user where id=#{id} and role=#{role}")
    User selectByIdAndRole(Integer id,Integer role);

    @Delete("delete from user where sid=#{sid} and role=#{role}")
    int deleteByIdAndRole(int sid, int role);

    List<User> getallUsersExceptAdmin(String id);


    List<User> checkUserFuzzyExceptAdmin(@Param("keyWord") String keyWord, @Param("id")String id);


    @Update("update user set psw=#{psw} where id=#{id} and role=#{role}")
    int updateByIdAndRole(Integer id, String psw, Integer role);

    /**
     * 查询用户的详细信息，这个是对应用户的所有详细信息
     * @param id
     * @param role
     * @return
     */
    User selectByIdAndRoleDetail(@Param("id")String id, @Param("role")int role);

    @Update("update `user` set phone=#{phone} where id=#{id} and role=#{role}")
    int updatePhone(Integer id, int role, String phone);

    @Insert("insert into `user`(id,psw,role,phone) value(#{id},#{psw},#{role},#{phone})")
    void restore(Integer id, String psw, String phone, Integer role);
}