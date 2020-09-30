package org.seckill.common.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException {

    private String exceptionCode;

    public BaseException(String code, String msg) {
        super(msg);
        this.exceptionCode = code;
    }

}
