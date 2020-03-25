package com.example.demo.service;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /*setRedis   String类型*/
    public void setString(String key,String value){
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key,value);
    }
    /*setRedis Hash类型*/
    public void setHash(String key,String filedKey,String value){
        HashOperations<String,Object,Object> hashOperations = stringRedisTemplate.opsForHash();
        hashOperations.put(key,filedKey,value);
    }
    /*setRedis List类型*/
    public void setList(String key,String value){
        ListOperations<String,String> listOperations = stringRedisTemplate.opsForList();
        listOperations.leftPush(key,value);
    }
    /*setRedis set类型*/
    public void setSet(String key,String value){
        SetOperations<String,String> setOperations = stringRedisTemplate.opsForSet();
        setOperations.add(key,value);
    }
    /*setRedis zset类型*/
    public void setZset(String key,String value,Double num){
        ZSetOperations<String,String> zSetOperations = stringRedisTemplate.opsForZSet();
        zSetOperations.add(key,value,num);
    }


    /*getRedis  String类型*/
    public String getString(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }
    /*getRedis Hash类型*/
    public String getHash(String key,String filedKey){
        return (String) stringRedisTemplate.opsForHash().get(key,filedKey);
    }
    /*getRedis List类型*/
    public List<String> getList(String key, Long start, Long end){
        return stringRedisTemplate.opsForList().range(key,start,end);
    }
    /*getRedis set类型*/
    public Set getSet(String key){
        return stringRedisTemplate.opsForSet().members(key);
    }
}
