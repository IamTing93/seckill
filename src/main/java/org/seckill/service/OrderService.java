package org.seckill.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.seckill.dao.GoodsDAO;
import org.seckill.dao.OrderInfoDAO;
import org.seckill.model.Goods;
import org.seckill.model.OrderInfo;
import org.seckill.model.User;
import org.seckill.vo.GoodsVO;
import org.seckill.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {

    @Autowired
    private OrderInfoDAO orderInfoDAO;

    @Autowired
    private GoodsDAO goodsDAO;

    public OrderInfo getOrderInfoByIds(int userId, int goodsId) {
        return orderInfoDAO.selectOne(new QueryWrapper<OrderInfo>().lambda()
                .eq(OrderInfo::getUserId, userId)
                .eq(OrderInfo::getGoodsId, goodsId));
    }

    public int createOrder(User user, Goods goods, OrderVO order) {
        OrderInfo orderInfo = OrderInfo.builder()
                .createDate(Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8)))
                .goodsCount(order.getCount())
                .goodsId(goods.getId())
                .goodsName(goods.getGoodsName())
                .goodsPrice(goods.getGoodsPrice())
                .userId(user.getId())
                .status(0)
                .build();
        orderInfoDAO.insert(orderInfo);
        return orderInfo.getId();
    }

    public int order(User user, GoodsVO goodsVO, OrderVO orderVO) {
        // 减去库存
        goodsDAO.reduceGoods(orderVO);
        // 创建订单
        return createOrder(user, goodsVO, orderVO);
    }
}
