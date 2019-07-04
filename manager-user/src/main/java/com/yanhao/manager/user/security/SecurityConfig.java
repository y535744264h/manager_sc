package com.yanhao.manager.user.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@EnableWebSecurity
public class SecurityConfig extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    public <O extends FilterSecurityInterceptor> O postProcess(
                            O fsi) {
                        fsi.setSecurityMetadataSource(mySecurityMetadataSource());
                        fsi.setAccessDecisionManager(myAccessDecisionManager());
                        return fsi;
                    }
                });
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId("WRIGTH")
                .tokenStore(jwtTokenStore());
    }

    @Bean
    public FilterInvocationSecurityMetadataSource mySecurityMetadataSource() {
        MyFilterInvocationSecurityMetadataSource securityMetadataSource = new MyFilterInvocationSecurityMetadataSource();
        return securityMetadataSource;
    }

    @Bean
    public AccessDecisionManager myAccessDecisionManager() {
        return new MyAccessDecisionManager();
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("springcloud123");
        return converter;
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtTokenConverter());
    }

}
