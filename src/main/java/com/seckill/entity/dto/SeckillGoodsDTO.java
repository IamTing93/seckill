package com.seckill.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeckillGoodsDTO extends GoodsDTO {

    private Integer seckillId;
    private Integer goodsId;
    private double seckillPrice;
    private int stockCount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}


