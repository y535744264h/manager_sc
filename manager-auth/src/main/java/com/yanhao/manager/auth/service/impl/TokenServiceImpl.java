package com.yanhao.manager.auth.service.impl;

import com.yanhao.manager.auth.client.RoleClient;
import com.yanhao.manager.auth.client.UserClient;
import com.yanhao.manager.auth.entity.Role;
import com.yanhao.manager.auth.entity.ServiceToken;
import com.yanhao.manager.auth.entity.User;
import com.yanhao.manager.auth.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TokenServiceImpl implements TokenService {


    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    UserClient userClient;

    @Autowired
    RoleClient roleClient;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;


    @Value("${auth.clientId}")
    private String clientId;

    @Value("${auth.grantType}")
    private String grantType;



    @Override
    public ServiceToken getServiceToken(String name, String pwd) {
        ServiceToken serviceToken=new ServiceToken(false,"",new Date(),name,pwd,null,"msg");
        User user=userClient.findUserByName(name);
        if(user!=null){
            if(user.getStatus()!=1){
                if(user.getUserPassword().equals(pwd)){
                    List<Role> roleList=roleClient.getRolesByUserId(user.getId());
                    Collection<GrantedAuthority> grantedAuthoritys=new ArrayList<GrantedAuthority>();
                    for (Role role : roleList) {
                        SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(role.getRoleCode());
                        grantedAuthoritys.add(simpleGrantedAuthority);
                    }
                    Authentication authentication=new UsernamePasswordAuthenticationToken(name,pwd,grantedAuthoritys);
                    ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
                    TokenRequest tokenRequest = new TokenRequest(new HashMap<String, String>(), clientId, clientDetails.getScope(),grantType);
                    OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
                    OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
                    OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
                    serviceToken.setResult(true);
                    serviceToken.setToken("Bearer "+oAuth2AccessToken.getValue());
                    serviceToken.setMsg("token 生成成功");
                }else{
                    serviceToken.setResult(false);
                    serviceToken.setMsg("用户名或密码不正确");
                }
            }else{
                serviceToken.setResult(false);
                serviceToken.setMsg("该用户已被禁用");
            }
        }else{
            serviceToken.setResult(false);
            serviceToken.setMsg("未找到该用户");
        }
        return serviceToken;

    }
}
