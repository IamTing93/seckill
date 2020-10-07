package org.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.seckill.vo.GoodsVO;
import org.seckill.vo.OrderVO;

@Mapper
public interface GoodsDAO extends BaseMapper<GoodsVO>{

    @Update("update goods set goods_stock = goods_stock - #{orderVO.count} where id = #{orderVO.goodsId}")
    int reduceGoods(@Param("orderVO") OrderVO orderVO);
}
