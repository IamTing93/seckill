package com.seckill.service.impl;

import com.seckill.entity.dto.SeckillGoodsDTO;
import com.seckill.mapper.SeckillGoodsMapper;
import com.seckill.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Override
    public List<SeckillGoodsDTO> listSeckillGoods() {
        return seckillGoodsMapper.listSeckillGoods();
    }

    @Override
    public SeckillGoodsDTO seckillGoodsDetail(long id) {
        return seckillGoodsMapper.seckillGoodsDetail(id);
    }
}
