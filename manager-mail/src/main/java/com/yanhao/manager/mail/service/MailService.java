package com.yanhao.manager.mail.service;

public interface MailService {

    boolean insertMail(String mail, String title, String content, int isFile, String fileUrl, int level);

    boolean insertMailByTemp(String mail,String[] titleParams,String[] contents,int isFile,String fileUrl,int level);
}
