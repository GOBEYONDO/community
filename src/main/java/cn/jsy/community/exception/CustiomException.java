package cn.jsy.community.exception;

import cn.jsy.community.enums.CustomErrorCode;

public class CustiomException extends RuntimeException {
    private String message;
    private Integer code;
    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public CustiomException(CustomErrorCode customErrorCode) {
        this.message = customErrorCode.getMessage();
    }

    public CustiomException(String message, String message1, Integer code) {
        super(message);
        this.message = message1;
        this.code = code;
    }
}
