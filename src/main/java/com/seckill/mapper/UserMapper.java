package com.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seckill.entity.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author boting.guo
 * @date 2020/11/10 19:30
 */

@Mapper
@Repository
public interface UserMapper extends BaseMapper<UserDTO> {
}
