package com.yanhao.manager.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${auth.clientId}")
    private String clientId;
    @Value("${auth.secret}")
    private String secret;

    @Value("${auth.grantType}")
    private String grantType;

    @Value("${auth.key}")
    private String key;

    /**
     * 来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //clientId：（必须的）用来标识客户的Id。
               .withClient(clientId)
                //secret：（需要值得信任的客户端）客户端安全码，如果有的话。
               .secret(secret)
                //scope 用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。
               .scopes("WRIGTH", "read").autoApprove(true)
                //authorities 此客户端可以使用的权限（基于Spring Security authorities）。
               .authorities("WRIGTH_READ", "WRIGTH_WRITE")
                //authorizedGrantTypes 此客户端可以使用的授权类型，默认为空。
               .authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code");
    }

    /**
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer security) throws Exception {
        security.tokenStore(jwtTokenStore())
                .tokenEnhancer(jwtTokenConverter())
                .authenticationManager(authenticationManager);
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtTokenConverter());
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(key);
        return converter;
    }
}
