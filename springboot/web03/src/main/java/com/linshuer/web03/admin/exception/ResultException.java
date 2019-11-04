package com.linshuer.web03.admin.exception;


import com.linshuer.web03.admin.enums.ResultEnums;

/**
 * @auther TyCoding
 * @date 2018/10/16
 */
public class ResultException extends RuntimeException {

    public ResultException(String message) {
        super(message);
    }

    public ResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultException(ResultEnums resultEnums) {
        
    }
}
