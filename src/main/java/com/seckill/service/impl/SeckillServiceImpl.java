package com.seckill.service.impl;

import com.seckill.common.CodeMsg;
import com.seckill.entity.SeckillStatus;
import com.seckill.entity.dto.SeckillGoodsDTO;
import com.seckill.entity.dto.UserDTO;
import com.seckill.exception.GlobalException;
import com.seckill.service.GoodsService;
import com.seckill.service.OrderService;
import com.seckill.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

/**
 * The type Seckill service.
 */
@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {

    @Value("${com.seckill.redis-lock.expire}")
    private int REDIS_LOCK_EXPIRE;

    @Value("${com.seckill.redis-lock.key-prefix}")
    private static String REDIS_LOCK_PREFIX;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @Override
    public SeckillStatus getSeckillGoodsStatus(SeckillGoodsDTO dto) {
        SeckillStatus status = new SeckillStatus();
        Instant now = Instant.now();
        Instant startDate = dto.getStartDate().toInstant(ZoneOffset.of("+8"));
        Instant endDate = dto.getEndDate().toInstant(ZoneOffset.of("+8"));
        if (now.compareTo(startDate) < 0) {
            status.setStatus(SeckillStatus.NOT_BEGIN);
            status.setRemainingSec(startDate.getEpochSecond() - now.getEpochSecond());
        } else if (now.compareTo(startDate) >= 0 && now.compareTo(endDate) < 0) {
            status.setStatus(SeckillStatus.UNDER_SECKILLING);
            status.setRemainingSec(0);
        } else if (now.compareTo(endDate) >= 0) {
            status.setStatus(SeckillStatus.END);
            status.setRemainingSec(-1L);
        }
        return status;
    }

    @Transactional
    @Override
    public boolean doSeckill(UserDTO user, int goodsId) {
        boolean rst;
        rst = doSeckill_redisLock(user, goodsId);
        return rst;
    }


    /**
     * 加redis分布式锁
     *
     * @param user
     * @param goodsId
     * @return
     */
    private boolean doSeckill_redisLock(UserDTO user, int goodsId) {
        String lockKey = REDIS_LOCK_PREFIX + user.getId() + "_" + goodsId;
        try {
            // 先上锁
            if (redisTemplate.opsForValue().setIfAbsent(lockKey, "1", REDIS_LOCK_EXPIRE, TimeUnit.SECONDS) == Boolean.TRUE) {
                // 先查看是否已经秒杀过
                boolean isSeckilled = orderService.isSeckillOrderExisted(user, goodsId);
                if (!isSeckilled) {
                    // 插入订单
                    log.info("user: " + user.getId() + " try to seckill");
                    if (!orderService.createSeckillOrder(user, goodsId)) {
                        throw new GlobalException(CodeMsg.ORDER_CREATE_FAIL);
                    }

                    // 这里利用数据库+事务来规避了订单创建过多的问题
                    // 实际上会有很多线程都会访问到这个语句
                    // 库存数量有可能小于请求数量，导致减扣失败
                    // 因为事务的缘故，会进行回滚

                    // 减库存
                    if (!goodsService.decreaseSeckillGoods(goodsId, 1)) {
                        throw new GlobalException(CodeMsg.PRODUCT_DECREASE_FAIL);
                    } else {
                        log.info("user: " + user.getId() + " successes to seckill");
                    }
                }
                return true;
            }
            return false;
        } finally {
            // 这里会有一个问题，若果加完锁后
            // 由于某些原因，有可能导致A线程加的锁被B线程给删掉了
            if (redisTemplate.hasKey(lockKey) == Boolean.TRUE) {
                redisTemplate.delete(lockKey);
            }
        }
    }
}
