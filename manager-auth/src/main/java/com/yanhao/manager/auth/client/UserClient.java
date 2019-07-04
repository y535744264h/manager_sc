package com.yanhao.manager.auth.client;

import com.yanhao.manager.auth.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("manager-user")
public interface UserClient {

    @RequestMapping(value = "/user/findUserByName",method = RequestMethod.POST)
    public User findUserByName(@RequestParam(value = "userName") String userName);
}
