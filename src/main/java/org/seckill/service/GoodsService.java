package org.seckill.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.seckill.dao.GoodsDAO;
import org.seckill.dao.SeckillGoodsDAO;
import org.seckill.vo.GoodsVO;
import org.seckill.vo.SeckillGoodsDetailVO;
import org.seckill.vo.SeckillGoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class GoodsService {

    @Autowired
    private SeckillGoodsDAO seckillGoodsDAO;

    @Autowired
    private GoodsDAO goodsDAO;

    public List<SeckillGoodsVO> listSeckillGoodsVO() {
        return seckillGoodsDAO.listSeckillGoodsVO();
    }

    public SeckillGoodsDetailVO getSeckillGoodsByGoodsId(int id) {

        SeckillGoodsVO goods = seckillGoodsDAO.getSeckillGoodsByGoodsId(id);

        // 0未开始，1进行中，2已结束
        int seckillStatus = 0;
        // 距离秒杀的秒数
        long remainSeconds = 0;

        long startAt = goods.getStartTime().getEpochSecond();
        long endAt = goods.getEndTime().getEpochSecond();
        long now = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8)).getEpochSecond();

        if (now < startAt) {
            seckillStatus = 0;
            remainSeconds = startAt - now;
        } else if (now > endAt) {
            seckillStatus = 2;
            remainSeconds = -1;
        } else {
            seckillStatus = 1;
            remainSeconds = 0;
        }

        return SeckillGoodsDetailVO.builder()
                .goods(goods)
                .remainSeconds(remainSeconds)
                .seckillStatus(seckillStatus)
                .build();
    }

    public GoodsVO getGoodsVOByGoodsId(int id) {
       return goodsDAO.selectById(id);
    }

    public List<GoodsVO> listGoodsVO() {
        return goodsDAO.selectList(new QueryWrapper<>());
    }
}
