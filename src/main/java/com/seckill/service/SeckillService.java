package com.seckill.service;

import com.seckill.entity.SeckillStatus;
import com.seckill.entity.dto.SeckillGoodsDTO;

public interface SeckillService {

    SeckillStatus getSeckillGoodsStatus(SeckillGoodsDTO dto);
}
