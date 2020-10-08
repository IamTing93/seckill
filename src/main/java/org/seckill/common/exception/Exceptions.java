package org.seckill.common.exception;

public class Exceptions {

    public static class StockEmpty extends BaseException {
        private static final String msg = "Stock Empty";
        public StockEmpty() {
            this(msg);
        }

        public StockEmpty(String msg) {
            super(ExceptionCode.EX_STOCK_EMPTY, msg);
        }
    }

    public static class DuplicatedOrder extends BaseException {
        private static final String msg = "Duplicated order";
        public DuplicatedOrder() {
            this(msg);
        }

        public DuplicatedOrder(String msg) {
            super(ExceptionCode.EX_DUPLICATED_ORDER, msg);
        }
    }

    public static class AccountEmpty extends BaseException {
        private static final String msg = "Account is empty";
        public AccountEmpty() {
            this(msg);
        }

        public AccountEmpty(String msg) {
            super(ExceptionCode.EX_ACCOUNT_EMPTY, msg);
        }
    }

    public static class AccountNotExist extends BaseException {
        private static final String msg = "Account not exist";
        public AccountNotExist() {
            this(msg);
        }

        public AccountNotExist(String msg) {
            super(ExceptionCode.EX_ACCOUNT_NOT_EXIST, msg);
        }
    }

    public static class AccountExist extends BaseException {
        private static final String msg = "Account exists";

        public AccountExist() {
            this(msg);
        }

        public AccountExist(String msg) {
            super(ExceptionCode.EX_ACCOUNT_EXIST, msg);
        }
    }

    public static class PasswordEmpty extends BaseException {
        private static final String msg = "Password is empty";
        public PasswordEmpty() {
            this(msg);
        }

        public PasswordEmpty(String msg) {
            super(ExceptionCode.EX_PASSWORD_EMPTY, msg);
        }
    }

    public static class PasswordError extends BaseException {
        private static final String msg = "Error password";
        public PasswordError() {
            this(msg);
        }

        public PasswordError(String msg) {
            super(ExceptionCode.EX_PASSWORD_ERROR, msg);
        }
    }

}
