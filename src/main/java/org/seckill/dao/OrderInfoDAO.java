package org.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.seckill.model.OrderInfo;

@Mapper
public interface OrderInfoDAO extends BaseMapper<OrderInfo> {

}
