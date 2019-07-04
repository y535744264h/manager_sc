package com.yanhao.manager.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableZuulProxy
@EnableOAuth2Sso
public class GateWayApplication extends WebSecurityConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class,args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/login", "/client/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .csrf()
            .disable();
    }
}
