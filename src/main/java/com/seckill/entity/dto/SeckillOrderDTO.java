package com.seckill.entity.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("seckill_order")
public class SeckillOrderDTO extends OrderDTO{

    @TableId
    private Long seckillId;
    private Long seckillUserId;
    private Long seckillOrderId;
    private Long seckillGoodsId;
}
