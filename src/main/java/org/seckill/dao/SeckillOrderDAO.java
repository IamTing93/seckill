package org.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.seckill.model.SeckillOrder;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SeckillOrderDAO extends BaseMapper<SeckillOrder> {
}
