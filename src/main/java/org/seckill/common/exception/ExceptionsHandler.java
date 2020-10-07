package org.seckill.common.exception;

import org.seckill.common.model.CommonResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public CommonResponse<String> handleException(HttpServletResponse response, Exception ex) {
        CommonResponse<String> rst = null;
        if (ex instanceof BaseException) {
            BaseException be = (BaseException) ex;
            rst = new CommonResponse<>(be.getExceptionCode(), be.getMessage(), "");
        }
        return rst;
    }
}
