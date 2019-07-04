package com.yanhao.manager.auth.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Configuration
public class FeignConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Value("${auth.userName}")
    private String userName;

    @Value("${auth.userPwd}")
    private String userPwd;

    @Value("${auth.roles}")
    private String roles;

    @Value("${auth.clientId}")
    private String clientId;




    org.slf4j.Logger log = LoggerFactory.getLogger(FeignConfiguration.class);

    @Bean
    public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }

    public class FeignBasicAuthRequestInterceptor  implements RequestInterceptor {

        public FeignBasicAuthRequestInterceptor() {

        }

        @Override
        public void apply(RequestTemplate template) {
            //TODO
            Collection<GrantedAuthority> grantedAuthoritys=new ArrayList<GrantedAuthority>();
            for (String s : roles.split(",")) {
                SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(s);
                grantedAuthoritys.add(simpleGrantedAuthority);
            }
            Authentication authentication=new UsernamePasswordAuthenticationToken(userName,userPwd,grantedAuthoritys);
            log.info("authentication is" +authentication.isAuthenticated());
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
            TokenRequest tokenRequest = new TokenRequest(new HashMap<String, String>(), clientId, clientDetails.getScope(),"authorization_code");
            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
            log.info("token is "+oAuth2AccessToken.getValue());
            template.header("authorization", "Bearer "+oAuth2AccessToken.getValue());
        }
    }

}
