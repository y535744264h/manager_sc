package com.yanhao.manager.mail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.yanhao.manager.mail.dao")
public class MailApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class,args);
    }
}
