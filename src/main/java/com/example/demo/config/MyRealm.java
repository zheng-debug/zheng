package com.example.demo.config;


import com.example.demo.entity.TUser;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class MyRealm  extends AuthorizingRealm {
    @Resource
    private UserService userService;

    //用来验证当前登录的用户，获取认证信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //给该用户设置角色，存在t_role表
        authorizationInfo.setRoles(userService.getRoles(username));
        //给用户设置权限，存在t_permission表
        authorizationInfo.setStringPermissions(userService.getPermission(username));
        return authorizationInfo;
    }

    //用来为当前登陆成功的用户授予权限和角色
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //根据token获取用户名
        String username = (String) authenticationToken.getPrincipal();
        //根据用户名获取用户信息
        TUser user = userService.queryUser(username);
        //如果用户不为空，则将用户信息写入session中
        if(user!=null){
            SecurityUtils.getSubject().getSession().setAttribute("user",user);
            //传入用户名及密码进行验证
            AuthenticationInfo authenticationInfo = new
                    SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),"MyRealm");
            return authenticationInfo;
        }else{
            return null;
        }
    }





}
