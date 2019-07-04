package com.yanhao.manager.user.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "manager-mail")
public interface MailClient {

    @RequestMapping(value = "/mail/createMail",method = RequestMethod.POST)
    public Map createMail(@RequestParam(value = "mail") String mail,
                          @RequestParam(value = "title") String title,
                          @RequestParam(value = "content") String content,
                          @RequestParam(value = "isFile") int isFile,
                          @RequestParam(value = "fileUrl") String fileUrl,
                          @RequestParam(value = "level") int level);

}
