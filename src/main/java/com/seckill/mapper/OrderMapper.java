package com.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seckill.entity.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author boting.guo
 * @date 2020/11/14 16:40
 */

@Mapper
@Repository
public interface OrderMapper extends BaseMapper<OrderDTO> {
}
