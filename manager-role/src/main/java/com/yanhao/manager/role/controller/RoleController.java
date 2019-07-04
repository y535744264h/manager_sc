package com.yanhao.manager.role.controller;

import com.yanhao.manager.role.dao.RoleMapper;
import com.yanhao.manager.role.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleMapper roleMapper;

    @RequestMapping("/findRolesByUserId")
    public List<Role> findRolesByUserId(Long userId){
        return roleMapper.selectByUserId(userId);
    }
}
