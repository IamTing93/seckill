package com.seckill.exception;

import com.seckill.common.CodeMsg;

/**
 * @author boting.guo
 * @date 2020/11/10 16:29
 */


public class GlobalException extends RuntimeException {

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
