package org.seckill.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.seckill.dao.SeckillGoodsDAO;
import org.seckill.dao.SeckillOrderDAO;
import org.seckill.model.SeckillOrder;
import org.seckill.model.User;
import org.seckill.vo.OrderVO;
import org.seckill.vo.SeckillGoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillOrderService {

    @Autowired
    private SeckillOrderDAO seckillOrderDAO;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SeckillGoodsDAO seckillGoodsDAO;

    public SeckillOrder getSeckillOrderByIds(int userId, int goodsId) {
        return seckillOrderDAO.selectOne(new QueryWrapper<SeckillOrder>().lambda()
                .eq(SeckillOrder::getUserId, userId)
                .eq(SeckillOrder::getGoodsId, goodsId));
    }

    public Integer seckill(User user, SeckillGoodsVO seckillGoodsVO, OrderVO orderVO) {

        // 减库存
        seckillGoodsDAO.reduceGoods(orderVO);

        // 创建订单
        Integer orderId = orderService.createOrder(user, seckillGoodsVO, orderVO);

        SeckillOrder seckillOrder = SeckillOrder.builder().orderId(orderId)
                .goodsId(seckillGoodsVO.getGoodsId())
                .userId(user.getId())
                .build();
        return seckillOrderDAO.insert(seckillOrder);
    }
}
