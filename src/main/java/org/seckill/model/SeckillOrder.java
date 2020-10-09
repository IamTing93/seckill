package org.seckill.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@TableName("seckill_order")
@Builder
public class SeckillOrder {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private Integer orderId;
}
