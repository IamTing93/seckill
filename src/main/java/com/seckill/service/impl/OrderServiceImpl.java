package com.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.seckill.entity.dto.OrderDTO;
import com.seckill.entity.dto.SeckillGoodsDTO;
import com.seckill.entity.dto.SeckillOrderDTO;
import com.seckill.entity.dto.UserDTO;
import com.seckill.mapper.OrderMapper;
import com.seckill.mapper.SeckillOrderMapper;
import com.seckill.service.GoodsService;
import com.seckill.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author boting.guo
 * @date 2020/11/16 13:35
 */

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private GoodsService goodsService;

    @Override
    @Transactional
    public boolean createOrder(UserDTO user, int goodsId) {
        return false;
    }

    @Override
    @Transactional
    public boolean createSeckillOrder(UserDTO user, int goodsId) {

        SeckillGoodsDTO goods = goodsService.seckillGoodsDetail(goodsId);

        OrderDTO order = OrderDTO.builder()
                .userId(user.getId()).goodsId(goods.getGoodsId()).goodsName(goods.getGoodsName())
                .goodsCount(1).goodsPrice(goods.getSeckillPrice()).deliveryAddrId(0L)
                .orderChannel(1).status(0).createDate(LocalDateTime.now(ZoneId.of("+8"))).payDate(null)
                .build();

        orderMapper.insert(order);

        SeckillOrderDTO seckillOrder = new SeckillOrderDTO(null, user.getId(), order.getId(), goodsId);

        seckillOrderMapper.insertSeckillOrder(seckillOrder);

        return true;
    }

    @Override
    public boolean isSeckillOrderExisted(UserDTO user, int goodsId) {
        int isSeckilled = seckillOrderMapper.selectCount(new QueryWrapper<SeckillOrderDTO>().lambda()
                .eq(SeckillOrderDTO::getSeckillUserId, user.getId())
                .eq(SeckillOrderDTO::getSeckillGoodsId, goodsId));
        return isSeckilled > 0;
    }
}
