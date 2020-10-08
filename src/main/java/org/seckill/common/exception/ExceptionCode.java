package org.seckill.common.exception;

public class ExceptionCode {
    public static final String EX_SUCCESS = "EX_0000";
    public static final String EX_FAIL = "EX_0001";
    public static final String EX_SYSTEM_ERROR = "EX_5000";

    // 账号相关
    public static final String EX_ACCOUNT_EMPTY = "EX_1001";
    public static final String EX_ACCOUNT_NOT_EXIST = "EX_1002";
    public static final String EX_ACCOUNT_EXIST = "EX_1003";
    public static final String EX_PASSWORD_EMPTY = "EX_1006";
    public static final String EX_PASSWORD_ERROR = "EX_1007";

    // 秒杀错误
    public static final String EX_STOCK_EMPTY = "EX_2001";
    public static final String EX_DUPLICATED_ORDER = "EX_2002";
}
