package org.seckill.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeckillGoodsDetailVO {

    private SeckillGoodsVO goods;
    private int seckillStatus;
    private long remainSeconds;
}
