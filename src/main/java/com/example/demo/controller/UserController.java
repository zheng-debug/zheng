package com.example.demo.controller;

import com.example.demo.entity.TUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    //身份认证接口
    @RequestMapping("/admin")
    public String admin(HttpServletRequest request){
        Object user = request.getSession().getAttribute("user");
        return "/html/index";
    }

    //角色认证接口
    @RequestMapping("/student")
    public String student(HttpServletRequest request){
        return "/html/index";
    }

    //权限认证接口
    @RequestMapping("/teacher")
    public String teacher(HttpServletRequest request){
        return "/html/index";
    }

    //用户登录接口
    @RequestMapping("/submit")
    public String submit(TUser user, HttpServletRequest request){
        //根据用户名密码创建token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        //获取subject认证主体
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            request.getSession().setAttribute("user",user);
            return "/html/index";
        }catch (Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("user",user);
            request.setAttribute("error","用户名或密码错误！");
            return "/html/login";
        }
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("username","hhhhh");
        return "/html/login";
    }
}
