package com.yanhao.manager.mail.entity;

import java.util.Date;

public class Mail {
    private Long id;

    private String mail;

    private String title;

    private String content;

    private Integer isFile;

    private String fileUrl;

    private Date createTime;

    private Date sendTime;

    private Integer sendState;

    private Integer sendErrorCount;

    private Integer level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getIsFile() {
        return isFile;
    }

    public void setIsFile(Integer isFile) {
        this.isFile = isFile;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getSendState() {
        return sendState;
    }

    public void setSendState(Integer sendState) {
        this.sendState = sendState;
    }

    public Integer getSendErrorCount() {
        return sendErrorCount;
    }

    public void setSendErrorCount(Integer sendErrorCount) {
        this.sendErrorCount = sendErrorCount;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}