package com.example.demo.config;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice//basePackages属性，用于指定拦截哪个包中的异常，默认拦截所有异常。
@ResponseBody
public class GlobalExceptionHandler {//全局异常处理类（当系统出现异常时，反馈一个较为友好的错误信息给用户，并且使用日志系统对错误信息进行记录）
    //打印日志
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*
    * 请求参数缺失异常
    */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JsonResult handleHttpMessageNotReadableException(MissingServletRequestParameterException ex){
        logger.error("请求缺失参数：{}",ex.getMessage());
        return new JsonResult("400","缺失必要的请求参数！");
    }

    /*
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleHttpMessageNotReadableException(NullPointerException ex){
        logger.error("空指针异常：{}",ex.getMessage());
        return new JsonResult("500","空指针异常！");
    }

    /*
     * 自定义异常
     */
    @ExceptionHandler(BusinessErrorException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleBusinessError(BusinessErrorException ex){
        logger.error("code:",ex.getCode());
        logger.error("msg:",ex.getMsg());
        return new JsonResult(ex.getCode(),ex.getMsg());
    }

    /*
     * 意料之外的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleHttpMessageNotReadableException(Exception ex){
        logger.error("意料之外的异常，请联系管理员进行处理：{}",ex.getMessage());
        return new JsonResult("500","意料之外的异常，请联系管理员进行处理！");
    }
}
