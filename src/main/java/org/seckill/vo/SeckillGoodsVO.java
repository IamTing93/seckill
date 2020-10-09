package org.seckill.vo;

import lombok.Data;
import org.seckill.model.Goods;

import java.time.Instant;

@Data
public class SeckillGoodsVO extends Goods {

    private Integer goodsId;
    private Double seckillPrice;
    private int seckillStock;
    private Instant startTime;
    private Instant endTime;
}
