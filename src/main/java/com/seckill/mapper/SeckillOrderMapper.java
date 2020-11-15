package com.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seckill.entity.dto.SeckillOrderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SeckillOrderMapper extends BaseMapper<SeckillOrderDTO> {

    SeckillOrderDTO getSeckillOrder(@Param("id") Long seckillOrderId);
}
