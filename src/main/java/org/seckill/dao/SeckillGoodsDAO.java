package org.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.seckill.vo.OrderVO;
import org.seckill.vo.SeckillGoodsVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SeckillGoodsDAO extends BaseMapper<SeckillGoodsVO> {

    @Select("select * from seckill_goods as sg left join goods on sg.goods_id = goods.id")
    List<SeckillGoodsVO> listSeckillGoodsVO();

    @Select("select * from seckill_goods as sg left join goods on sg.goods_id = goods.id where sg.goods_id = #{id}")
    SeckillGoodsVO getSeckillGoodsByGoodsId(int id);

    @Update("update seckill_goods set seckill_stock = seckill_stock - 1 where id = #{orderVO.goodsId}")
    int reduceGoods(@Param("orderVO") OrderVO orderVO);
}
