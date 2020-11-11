package com.seckill.service;

import com.seckill.entity.dto.GoodsDTO;

import java.util.List;

public interface GoodsService {

    List<GoodsDTO> listGoods();

    GoodsDTO goodsDetail(long id);
}
