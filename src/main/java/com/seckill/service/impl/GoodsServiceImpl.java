package com.seckill.service.impl;

import com.seckill.entity.dto.GoodsDTO;
import com.seckill.mapper.GoodsMapper;
import com.seckill.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsDTO> listGoods() {
        return goodsMapper.selectList(null);
    }
}
