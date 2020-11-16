package com.seckill.service;

import com.seckill.entity.dto.GoodsDTO;
import com.seckill.entity.dto.SeckillGoodsDTO;

import java.util.List;

public interface GoodsService {

    List<SeckillGoodsDTO> listSeckillGoods();

    SeckillGoodsDTO seckillGoodsDetail(long id);

    GoodsDTO goodsDetail(long id);

    boolean decreaseSeckillGoods(long id, int num);
}
