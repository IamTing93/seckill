package com.seckill.service.impl;

import com.seckill.entity.dto.GoodsDTO;
import com.seckill.entity.dto.SeckillGoodsDTO;
import com.seckill.mapper.GoodsMapper;
import com.seckill.mapper.SeckillGoodsMapper;
import com.seckill.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<SeckillGoodsDTO> listSeckillGoods() {
        return seckillGoodsMapper.listSeckillGoods();
    }

    @Override
    public SeckillGoodsDTO seckillGoodsDetail(long id) {
        return seckillGoodsMapper.seckillGoodsDetail(id);
    }

    @Override
    public GoodsDTO goodsDetail(long id) {
        return goodsMapper.selectById(id);
    }

    @Override
    @Transactional
    public boolean decreaseSeckillGoods(long id, int num) {
        SeckillGoodsDTO goods = seckillGoodsMapper.seckillGoodsDetail(id);
        return goodsMapper.decreaseGoods(goods.getGoodsId(), num) && seckillGoodsMapper.decreaseSeckillGoods(id, num);
    }
}
