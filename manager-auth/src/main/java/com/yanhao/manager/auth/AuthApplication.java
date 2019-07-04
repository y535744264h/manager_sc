package com.yanhao.manager.auth;

import com.yanhao.manager.auth.config.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
/**WebSecurityConfigurerAdapter
 * 要求用户在进入你的应用的任何URL之前都进行验证
 * 创建一个用户名是“admin”，密码是“admin”
 * Spring Security将会自动生成一个登陆页面和登出成功页面
 */
public class AuthApplication extends WebSecurityConfigurerAdapter{



    @Autowired
    CustomUserDetailService customUserDetailService;

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class,args);
    }

    /**
     * 用于构建认证 AuthenticationManager 认证，允许快速构建内存认证、LDAP身份认证、JDBC身份验证，添加 userDetailsService（获取认证信息数据） 和 AuthenticationProvider's（定义认证方式）
     * 内存验证
     * @param auth
     * @throws Exception
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//            .withUser("guest").password("guest").authorities("WRIGTH_READ")
//            .and()
//            .withUser("admin").password("admin").authorities("WRIGTH_READ", "WRIGTH_WRITE");
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/system/**").permitAll()
                .and()
                .formLogin();
    }

    /**
     * 设置明文密码
     * @return
     */
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }


    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
