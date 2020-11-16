package com.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seckill.entity.dto.SeckillGoodsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SeckillGoodsMapper extends BaseMapper<SeckillGoodsDTO> {

    List<SeckillGoodsDTO> listSeckillGoods();

    SeckillGoodsDTO seckillGoodsDetail(@Param("id") long id);

    boolean decreaseSeckillGoods(long id, int num);
}
