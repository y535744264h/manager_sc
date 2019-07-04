package com.yanhao.manager.auth.entity;

import java.util.Date;
import java.util.List;

public class ServiceToken {

    //结果 true /false
    private boolean result;

    //token 正确的时候会有
    private String token;

    //生成时间
    private Date createTime;

    private String userName;

    private String userPwd;

    private List<String> roles;

    private String msg;



    public ServiceToken(boolean result, String token, Date createTime, String userName, String userPwd, List<String> roles, String msg) {
        this.result = result;
        this.token = token;
        this.createTime = createTime;
        this.userName = userName;
        this.userPwd = userPwd;
        this.roles = roles;
        this.msg = msg;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
