package com.yanhao.manager.auth.config;

import com.yanhao.manager.auth.entity.Role;
import com.yanhao.manager.auth.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private Long id;
    private String userName;
    private String passWord;
    private List<Role> roleList;

    public SecurityUser(){}

    public SecurityUser(User user, List<Role> roleList){
        super();
        this.userName=user.getUserAccount();
        this.passWord=user.getUserPassword();
        if(roleList==null){
            roleList=new ArrayList<Role>();
        }
        this.roleList=roleList;

    }
    public SecurityUser(User user){
        super();
        this.userName=user.getUserAccount();
        this.passWord=user.getUserPassword();
        this.roleList=new ArrayList<Role>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
        for (Role role : roleList) {
            SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(role.getRoleCode());
            authorities.add(simpleGrantedAuthority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.passWord;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    //判断账号是否已经过期，默认没有过期
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    //判断账号是否被锁定，默认没有锁定
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    //判断信用凭证是否过期，默认没有过期
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    //判断账号是否可用，默认可用
    public boolean isEnabled() {
        return true;
    }
}
