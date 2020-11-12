package com.seckill.service.impl;

import com.seckill.entity.SeckillStatus;
import com.seckill.entity.dto.SeckillGoodsDTO;
import com.seckill.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;

@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {

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
}
