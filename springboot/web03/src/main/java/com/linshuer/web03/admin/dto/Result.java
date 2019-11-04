package com.linshuer.web03.admin.dto;

public class Result {
    private Integer code;
    private Object data;

    public Result(){}

    public Result(Integer code, Object data){
        this.code = code;
        this.data = data;
    }

    public Result(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
