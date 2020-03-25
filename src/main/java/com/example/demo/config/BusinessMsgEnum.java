package com.example.demo.config;

/*业务异常信息提示枚举类*/
public enum BusinessMsgEnum {

    /*参数异常*/
    PARMETER_EXCEPTION("100","参数异常！"),
    /*等待超时*/
    SERVICE_TIME_OUT("101","服务器响应超时！");


    private String code;
    private String msg;

    BusinessMsgEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
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
