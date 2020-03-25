package com.example.demo.config;

public class BusinessErrorException extends RuntimeException{
    private String code;
    private String msg;


    public BusinessErrorException(BusinessMsgEnum msgEnum) {
        this.code = msgEnum.getCode();
        this.msg = msgEnum.getMsg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
