package com.yanhao.manager.mail.controller;

import com.yanhao.manager.mail.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mail")
public class MailController {

    Logger log = LoggerFactory.getLogger(MailController.class);

    @Autowired
    MailService mailService;

    @RequestMapping("/createMail")
    public Map<String,Object> createMail(String mail, String title, String content, int isFile, String fileUrl, int level){
        log.info("----mail is {} title is {} content is {} isFile is {} fileUrl is {} level is {}---",mail,title,content,isFile,fileUrl,level);
        Map<String,Object> map =new HashMap<String, Object>();
        boolean bool=mailService.insertMail(mail,title,content,isFile,fileUrl,level);
        if(bool){
            map.put("state","success");
        }else{
            map.put("state","error");
        }
        return map;
    }
}
