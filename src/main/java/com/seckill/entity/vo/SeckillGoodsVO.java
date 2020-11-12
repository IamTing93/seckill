package com.seckill.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeckillGoodsVO extends GoodsVO {

    private Long seckillId;
    private Long goodsId;
    private double seckillPrice;
    private int stockCount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
