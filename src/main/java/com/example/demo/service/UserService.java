package com.example.demo.service;

import com.example.demo.dao.UserMapper;
import com.example.demo.entity.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.annotation.Resource;
import javax.xml.ws.ServiceMode;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public Set<String> getRoles(String username){
       Set<String> set = new HashSet<>();
       set = userMapper.getRoles(username);
       return set;
    }

    public Set<String> getPermission(String username){
        Set<String> set = new HashSet<>();
        set = userMapper.getPermission(username);
        return set;
    }

    public TUser queryUser(String username){
        TUser user = new TUser();
        user = userMapper.queryUser(username);
        return user;
    }

}
