package com.example.demo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspectHandler { //面向切面类

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /*
    1.@Pointcut：定义一个切面，即上面所描述的关注的某件事入口。
    2.@Before：在做某件事之前做的事。
    3.@After：在做某件事之后做的事。
    4.@AfterReturning：在做某件事之后，对其返回值做增强处理。
    5.@AfterThrowing：在做某件事抛出异常时，处理。
    */


    //拦截指定包下面得所有方法,execution注解，用于定义拦截指定路径下的包，annotation()注解，用于切入使用了指定注解得方法
    /*@Pointcut("execution(* com.example.demo..*.*(..)))")
    public void pointcut(){
        logger.info("--------------拦截到了controller包下的所有方法！");
    }*/

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void annotation(){
        logger.info("--------------------拦截到了使用GetMapping的方法---------");
    }

    @Before("annotation()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("------------进入before方法--------------");

        //获取签名
        Signature signature = joinPoint.getSignature();
        //获取切入的包名
        String declearingType = signature.getDeclaringTypeName();
        //获取即将执行的方法名
        String funcName = signature.getName();
        logger.info("即将执行的方法名为:"+funcName+"--所在包为:"+declearingType);

        //记录部分信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        logger.info("用户请求的Url:"+url+"--用户ip:"+ip);
    }
}
