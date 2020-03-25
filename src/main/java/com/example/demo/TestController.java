package com.example.demo;

import com.example.demo.config.JsonResult;
import com.example.demo.config.MicroServiceUrl;
import com.example.demo.dao.User;
import com.example.demo.entity.TUser;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;
/*mybaits集成并未成功，此后仍需研究！*/
@Controller
@RequestMapping("/testController")
public class TestController {
    private final static Logger logger = LoggerFactory.getLogger(TestController.class);
    @Value("${url.orderUrl}")
    private String url;

    @Resource
    private MicroServiceUrl microServiceUrl;
    @Resource
    private UserService userService;


    @RequestMapping("/hello")
    public JsonResult<User> hello(){
        User user = new User("郑嘉成","zhengjiacheng",null);
        logger.debug("--------------输出debug层级的日志--------------");
        logger.error("--------------输出error层级的日志--------------");
        logger.info("--------------输出info层级的日志--------------");
        logger.warn("--------------输出warn层级的日志--------------");
        return new JsonResult<>(user,"2","操作失败！");
    }

    @RequestMapping("/login")
    public String config(){
        return "/html/login";
    }

    @RequestMapping("/configs")
    public String configs(){
        logger.info("订单服务地址为："+ microServiceUrl.getOrderUrl());
        logger.info("购物服务地址为："+ microServiceUrl.getShopUrl());
        return microServiceUrl.getShopUrl()+microServiceUrl.getOrderUrl();
    }

    /*@PathVariable注释用于从路径模板中获取参数信息。@RequestParam注释用于从request请求中获取注释
    * 以上两种注释，当请求参数与接收参数不一致时，则需采用value属性来进行说明。
    * 当前端通过表单提交，参数较多时，可以直接通过实体来进行接收，则无需添加任何注释。*/
    @PutMapping("/user/{name}/{pass}")
    public String pathVariable(@PathVariable(value = "name")String xingm ,@PathVariable String pass){
        logger.info("姓名为："+xingm);
        logger.info("密码为："+pass);
        return "姓名密码-"+xingm+"/"+pass;
    }


    @RequestMapping("/user")
    public String requestParam(@RequestParam(value = "name")String xingm ,@RequestParam String pass){
        logger.info("姓名为："+xingm);
        logger.info("密码为："+pass);
        return "姓名密码-"+xingm+"/"+pass;
    }


}
