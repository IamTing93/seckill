package com.seckill.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("order_info")
public class OrderDTO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Long userId;
    private Integer goodsId;
    private String goodsName;
    private Integer goodsCount;
    private Double goodsPrice;
    private Long deliveryAddrId;
    private Integer orderChannel;
    private Integer status;
    private LocalDateTime createDate;
    private LocalDateTime payDate;
}
