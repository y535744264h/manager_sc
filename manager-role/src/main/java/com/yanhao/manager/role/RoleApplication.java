package com.yanhao.manager.role;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.yanhao.manager.role.dao")
public class RoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoleApplication.class,args);
    }
}
