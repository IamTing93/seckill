package com.seckill.service;

import com.seckill.entity.SeckillStatus;
import com.seckill.entity.dto.SeckillGoodsDTO;
import com.seckill.entity.dto.UserDTO;

public interface SeckillService {

    SeckillStatus getSeckillGoodsStatus(SeckillGoodsDTO dto);
    boolean doSeckill(UserDTO user, int goodsId);
}
