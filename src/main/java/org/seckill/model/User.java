package org.seckill.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@TableName("user")
public class User {

    @TableId("id")
    private Integer id;
    private String name;
    @JsonIgnore
    private String password;
    private String salt;
    private String headUrl;
}
