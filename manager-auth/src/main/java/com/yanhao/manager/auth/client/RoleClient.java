package com.yanhao.manager.auth.client;

import com.yanhao.manager.auth.entity.Role;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("manager-role")
public interface RoleClient {

    @RequestMapping(value = "/role/findRolesByUserId",method = RequestMethod.POST)
    public List<Role> getRolesByUserId(@RequestParam(value = "userId") Long userId);
}
