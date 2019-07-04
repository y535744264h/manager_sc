package com.yanhao.manager.mail.dao;

import com.yanhao.manager.mail.entity.Mail;

public interface MailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Mail record);

    int insertSelective(Mail record);

    Mail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Mail record);

    int updateByPrimaryKey(Mail record);
}