package com.seckill.exception;

import com.seckill.common.CodeMsg;
import com.seckill.entity.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author boting.guo
 * @date 2020/11/10 16:38
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ServerResponse<CodeMsg> handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {

        log.info(e.getMessage());

        if (e instanceof GlobalException) {
            return ServerResponse.error(((GlobalException) e).getCodeMsg());
        }

        return ServerResponse.error(CodeMsg.SERVER_ERROR);
    }
}
