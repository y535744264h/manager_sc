package com.yanhao.manager.mail.service.impl;

import com.yanhao.manager.mail.dao.MailMapper;
import com.yanhao.manager.mail.entity.Mail;
import com.yanhao.manager.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class MailServiceImpl implements MailService {

    @Autowired
    MailMapper mailMapper;

    @Override
    public boolean insertMail(String mail, String title, String content, int isFile, String fileUrl, int level) {
        Mail sendmail=new Mail();
        sendmail.setId(null);
        sendmail.setMail(mail);
        sendmail.setTitle(title);
        sendmail.setContent(content);
        sendmail.setCreateTime(new Date());
        sendmail.setSendState(0);
        sendmail.setIsFile(isFile);
        sendmail.setFileUrl(fileUrl);
        sendmail.setLevel(level);
        sendmail.setSendErrorCount(0);
        int role=mailMapper.insert(sendmail);
        if(role==0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean insertMailByTemp(String mail, String[] titleParams, String[] contents, int isFile, String fileUrl, int level) {
        return false;
    }
}
