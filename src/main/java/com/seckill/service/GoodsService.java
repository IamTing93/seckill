package com.seckill.service;

import com.seckill.entity.dto.SeckillGoodsDTO;

import java.util.List;

public interface GoodsService {

    List<SeckillGoodsDTO> listSeckillGoods();

    SeckillGoodsDTO seckillGoodsDetail(long id);
}
