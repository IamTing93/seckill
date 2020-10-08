package org.seckill.controller;

import org.seckill.access.CurrentUser;
import org.seckill.common.exception.Exceptions;
import org.seckill.common.model.CommonResponse;
import org.seckill.model.OrderInfo;
import org.seckill.model.User;
import org.seckill.service.GoodsService;
import org.seckill.service.OrderService;
import org.seckill.vo.GoodsVO;
import org.seckill.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @PostMapping
    public CommonResponse<Integer> order(@CurrentUser User user, @RequestBody OrderVO orderVO) {
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
