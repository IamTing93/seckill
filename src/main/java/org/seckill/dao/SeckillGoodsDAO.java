package org.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.seckill.vo.SeckillGoodsVO;

import java.util.List;

@Mapper
public interface SeckillGoodsDAO extends BaseMapper<SeckillGoodsVO> {

    @Select("select * from seckill_goods as sg left join goods on sg.goods_id = goods.id")
    List<SeckillGoodsVO> listSeckillGoodsVO();

    @Select("select * from seckill_goods as sg left join goods on sg.goods_id = goods.id where sg.goods_id = #{id}")
    SeckillGoodsVO getSeckillGoodsByGoodsId(int id);
}
