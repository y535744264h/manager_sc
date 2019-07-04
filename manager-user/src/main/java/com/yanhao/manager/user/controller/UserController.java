package com.yanhao.manager.user.controller;

import com.yanhao.manager.user.client.MailClient;
import com.yanhao.manager.user.dao.UserMapper;
import com.yanhao.manager.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    MailClient mailClient;

    Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/find/{id}")
    public User findUserById(@PathVariable Long id){
        log.info("----fund user id is {} -----",id);
        return  userMapper.selectByPrimaryKey(id);
    }

    @RequestMapping("/findUserByName")
    public User findUserByName(String userName){
        User user=userMapper.checkUserStatus(userName);
        return user;
    }

    @RequestMapping("/login")
    public Map<String,Object> loginUserByNamePwd(String userName, String userPassword){
        log.info("---login user userName is{0} password is {1}",userName,userPassword);
        User user=userMapper.checkUserStatus(userName);
        Map<String,Object> map =new HashMap<String, Object>();
        map.put("userName",userName);
        map.put("userPassword",userPassword);
        if(user!=null){
            if(user.getUserPassword().equals(userPassword)){
                map.put("state",1);
                map.put("msg","success");
            }else{
                map.put("state",2);
                map.put("msg","密码错误");
            }
        }else{
            map.put("status",0);
            map.put("msg","用户不存在");
        }
        return map;
    }


    @RequestMapping("/sendVerificationCode")
    public Map<String,Object> sendVerificationCode(String sendMail){
        //String mail, String title, String content, int isFile, String fileUrl, int level
        String mail=sendMail;
        String title="验证码";
        String content="您的验证码为 1234";
        int isFile=0;
        int level=1;
        //Map<String,Object> stringMap=restTemplate.getForObject("http://manager-mail/mail/createMail?mail="+mail+"&title="+title+"&content="+content+"&isFile="+isFile+"&level="+level,Map.class);
        Map<String,Object> stringMap=mailClient.createMail(mail,title,content,isFile,null,level);
        return stringMap;
    }
}
