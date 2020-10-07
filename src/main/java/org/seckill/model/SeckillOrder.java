package org.seckill.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("seckill_order")
public class SeckillOrder {

    @TableId
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private Integer orderId;
}
