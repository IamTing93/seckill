package com.seckill.service;

import com.seckill.entity.dto.UserDTO;

/**
 * @author boting.guo
 * @date 2020/11/16 13:33
 */


public interface OrderService {

    boolean createOrder(UserDTO user, int goodsId);
    boolean createSeckillOrder(UserDTO user, int goodsId);

    boolean isSeckillOrderExisted(UserDTO user, int goodsId);
}
