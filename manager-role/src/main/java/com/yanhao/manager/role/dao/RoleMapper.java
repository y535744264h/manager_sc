package com.yanhao.manager.role.dao;

import com.yanhao.manager.role.entity.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectByUserId(Long userId);
}