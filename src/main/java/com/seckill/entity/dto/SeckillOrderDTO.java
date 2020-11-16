package com.seckill.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("seckill_order")
public class SeckillOrderDTO extends OrderDTO {

    private Integer seckillId;
    private Long seckillUserId;
    private Integer seckillOrderId;
    private Integer seckillGoodsId;
}
