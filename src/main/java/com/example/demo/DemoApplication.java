package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//取消配置数据库等操作
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@MapperScan(value = "com.example.demo")//扫描指定包下面的所有Mapper文件
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("启动完毕！");
    }
}
