package org.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.seckill.model.User;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDAO extends BaseMapper<User> {
}
