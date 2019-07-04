package com.yanhao.manager.user.dao;

import com.yanhao.manager.user.entity.User;
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User checkUserStatus(String userName);
}