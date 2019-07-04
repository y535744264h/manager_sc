package com.yanhao.manager.auth.config;

import com.yanhao.manager.auth.client.RoleClient;
import com.yanhao.manager.auth.client.UserClient;
import com.yanhao.manager.auth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserClient userClient;

    @Autowired
    RoleClient roleClient;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user=userClient.findUserByName(userName);
        if(user==null){
            throw new UsernameNotFoundException("没有该用户");
        }else{
            return new SecurityUser(user, roleClient.getRolesByUserId(user.getId()));
        }
    }
}
