package com.yanhao.manager.auth.controller;

import com.yanhao.manager.auth.entity.ServiceToken;
import com.yanhao.manager.auth.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class AuthController {

    @Autowired
    TokenService tokenService;

    @RequestMapping("/getServiceToken")
    public ServiceToken getServiceToken(String userName, String userPwd){
        return tokenService.getServiceToken(userName,userPwd);
    }

}
