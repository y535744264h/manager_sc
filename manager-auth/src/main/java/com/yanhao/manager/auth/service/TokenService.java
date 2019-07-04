package com.yanhao.manager.auth.service;

import com.yanhao.manager.auth.entity.ServiceToken;

public interface TokenService{

    public ServiceToken getServiceToken(String name, String pwd);
}
