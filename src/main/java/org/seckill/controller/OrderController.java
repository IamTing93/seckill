package org.seckill.controller;

import org.seckill.common.exception.Exceptions;
import org.seckill.common.model.CommonResponse;
import org.seckill.model.OrderInfo;
import org.seckill.model.SeckillOrder;
import org.seckill.model.User;
import org.seckill.service.GoodsService;
import org.seckill.service.OrderService;
import org.seckill.vo.GoodsVO;
import org.seckill.vo.OrderVO;
import org.seckill.vo.SeckillGoodsDetailVO;
import org.seckill.vo.SeckillGoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @PostMapping
    public CommonResponse<Integer> order(@RequestBody OrderVO orderVO) {
        User user = new User();
        user.setId(1);
        // 判断库存
        GoodsVO goods = goodsService.getGoodsVOByGoodsId(orderVO.getGoodsId());

        if (goods.getGoodsStock() <= 0) {
            throw new Exceptions.StockEmpty();
        }
        // 是否已经下单
        OrderInfo orderInfo = orderService.getOrderInfoByIds(user.getId(), orderVO.getGoodsId());
        if (!Objects.isNull(orderInfo)) {
            throw new Exceptions.DuplicatedOrder();
        }

        // 执行下单
        Integer orderId = orderService.order(user, goods, orderVO);
        return CommonResponse.success(orderId);
    }
}
