package com.example.demo.config;

public class JsonResult<T> {
    public T date;
    public String code;
    public String msg;

    /*若没有数据返回，则默认为code=0，msg=“操作失败”*/
    public JsonResult() {
        this.code="0";
        this.msg="操作成功!";
    }

    /*若没有数据返回，则code和msg都可以人为设置*/
    public JsonResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /*若存在返回数据，则默认code=1，msg="操作成功"*/
    public JsonResult(T date) {
        this.date = date;
        this.code = "1";
        this.msg = "操作成功";
    }

    /*若存在返回数据，则code和msg都可以人为设置*/
    public JsonResult(T date, String code, String msg) {
        this.date = date;
        this.code = code;
        this.msg = msg;
    }
}
