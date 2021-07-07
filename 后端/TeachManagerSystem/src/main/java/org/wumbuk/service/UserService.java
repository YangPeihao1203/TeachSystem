package org.wumbuk.service;

import org.wumbuk.entity.User;

import java.util.List;

public interface UserService {


    //登录的验证操作
    public  int login(User user);


    //通过用户账号获得用户电话号
    public String getPhoneByIdAndRole(User user);


    /**
     * 通过uid和i查询用户
     * @param uid
     * @param i
     * @return
     */
    User getInformation(String uid, int i);

    /**
     * 获取除了admin之外的所有用户列表
     * @param id
     * @return
     */
    List<User> getallUsersExceptAdmin(String id);


    /**
     * 模糊查询除了Admin的id之外的其余的符合关键词的结果集合！
     * @param keyWord
     * @param id
     * @return
     */
    List<User> checkUserFuzzyExceptAdmin(String keyWord, String id);

    //重置用户编号为id的用户的密码为psw
    //该psw为加密前内容，所以要注意在方法中重置
    int resetPsw(int id, String psw,int role) throws Exception;

    int updateAvatar(User user);

}
