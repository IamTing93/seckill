package org.seckill.controller;

import org.seckill.access.CurrentUser;
import org.seckill.common.exception.Exceptions;
import org.seckill.common.model.CommonResponse;
import org.seckill.model.SeckillOrder;
import org.seckill.model.User;
import org.seckill.service.GoodsService;
import org.seckill.service.SeckillOrderService;
import org.seckill.vo.OrderVO;
import org.seckill.vo.SeckillGoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("seckill")
public class SeckillController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SeckillOrderService seckillOrderService;

    @PostMapping
    public CommonResponse<Integer> seckill(@CurrentUser User user, @RequestBody OrderVO orderVO) {
        // 判断库存
        SeckillGoodsVO goods = goodsService.getSeckillGoodsByGoodsId(orderVO.getGoodsId());

        if (goods.getSeckillStock() <= 0) {
            throw new Exceptions.StockEmpty();
        }
        // 是否已经下单
        SeckillOrder seckillOrder = seckillOrderService.getSeckillOrderByIds(user.getId(), orderVO.getGoodsId());
        if (!Objects.isNull(seckillOrder)) {
            throw new Exceptions.DuplicatedOrder();
        }

        // 执行下单
        Integer orderId = seckillOrderService.seckill(user, goods, orderVO);
        return CommonResponse.success(orderId);
    }
}
