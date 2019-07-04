package com.yanhao.manager.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RestController
@EnableResourceServer
@MapperScan("com.yanhao.manager.user.dao")
public class UserApplacation{
    public static void main(String[] args) {
        SpringApplication.run(UserApplacation.class,args);
    }



//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }

    @RequestMapping("/test")
    public String test(HttpServletRequest request) {
        System.out.println("----------------header----------------");
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            System.out.println(key + ": " + request.getHeader(key));
        }
        System.out.println("----------------header----------------");
        return "hellooooooooooooooo!";
    }
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                //启用基于 HttpServletRequest 的访问限制，开始配置哪些URL需要被保护、哪些不需要被保护
//            .authorizeRequests()
//            .antMatchers("/**").authenticated()
//            .antMatchers(HttpMethod.GET, "/test")
//            .hasAuthority("WRIGTH_READ");
//    }



}
