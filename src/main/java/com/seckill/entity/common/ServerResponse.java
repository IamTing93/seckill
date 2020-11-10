package com.seckill.entity.common;


import com.seckill.common.CodeMsg;

/**
 * The type Server response.
 *
 * @param <T> the type parameter
 * @author boting.guo
 * @date 2020 /11/10 16:13
 */
public class ServerResponse<T> {

    private int code;
    private String msg;
    private T data;

    private ServerResponse(CodeMsg codeMsg, T data) {
        if (codeMsg == null) {
            return;
        }
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
        this.data = data;
    }

    private ServerResponse(CodeMsg codeMsg) {
        this(codeMsg, null);
    }

    public static <T> ServerResponse<T> success(T data) {
        return new ServerResponse<>(CodeMsg.SUCCESS, data);
    }

    public static <T> ServerResponse<T> error(CodeMsg codeMsg) {
        return new ServerResponse<>(codeMsg);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
