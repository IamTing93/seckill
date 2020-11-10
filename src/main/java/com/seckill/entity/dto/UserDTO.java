package com.seckill.entity.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author boting.guo
 * @date 2020/11/10 19:31
 */

@Data
@Builder
@TableName("seckill_user")
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @TableId
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private LocalDateTime registerDate;
    private LocalDateTime lastLoginDate;
    private Integer loginCount;
}
