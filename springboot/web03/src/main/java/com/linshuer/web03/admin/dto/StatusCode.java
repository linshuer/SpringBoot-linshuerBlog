package com.linshuer.web03.admin.dto;

public class StatusCode {
    public static final int SUCCESS = 200; //成功
    public static final int ERROR = 201; //失败
    public static final int LOGINE_RROR = 202; //用户名或密码错误
    public static final int ACCESS_ERROR = 203; //权限不足
    public static final int REPEE_RROR = 204; //重复操作
    public static final int PARAMETER_ERROR = 205; //入参错误
}
