package com.example.demo.controller;

import com.example.demo.config.JsonResult;
import com.example.demo.dao.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swagger")
@Api(value = "Swagger2-在线接口文档") //表明这个类是Swagger的资源
public class SwaggerController {
    @GetMapping("/get/{username}")
    @ApiOperation(value = "根据用户名获取用户信息")//表示一个http请求的操作
    //@ApiParam 用于表明参数信息
    public JsonResult<User> getUser(@PathVariable @ApiParam(value = "用户名") String username){
        User user = new User("郑嘉成",username,"123456");
        return new JsonResult(user);
    }
}
