package com.example.demo.entity;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Map;

@Component
@Validated //数据校验
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private String ageAng;
    private Map<String, Object> map;
    private List<Object> list;
    @Email(message = "你输入的是个锤子邮箱！")
    private String email;

    public String getAgeAng() {
        return ageAng;
    }

    public void setAgeAng(String ageAng) {
        this.ageAng = ageAng;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person(String name, String ageAng, Map<String, Object> map, List<Object> list, @Email(message = "你输入的是个锤子邮箱！") String email) {
        this.name = name;
        this.ageAng = ageAng;
        this.map = map;
        this.list = list;
        this.email = email;
    }


    public Person() {
    }

    public Person(String name, String ageAng, Map<String, Object> map, List<Object> list) {
        this.name = name;
        this.ageAng = ageAng;
        this.map = map;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public String getageAng() {
        return ageAng;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public List<Object> getList() {
        return list;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setageAng(String ageAng) {
        this.ageAng = ageAng;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", ageAng='" + ageAng + '\'' +
                ", map=" + map +
                ", list=" + list +
                '}';
    }
}
