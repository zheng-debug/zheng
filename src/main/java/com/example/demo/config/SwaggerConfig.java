package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                //指定构建api文档的详细信息的方法.apiInfo
                .apiInfo(apiInfo())
                .select()
                //指定要生成api接口的包路径，这里吧controller作为包路径，生成controller中的所有接口
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    /*构建api文档的详细信息*/
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //设置标题
                .title("SpringBoot的学习-Swagger")
                //设置接口描述
                .description("Swagger-项目接口文档Api")
                //联系方式
                .contact("暂无联系地址")
                //版本信息
                .version("1.0")
                .build();
    }

}
