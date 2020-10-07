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
}
