package com.seckill.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

/**
 * @author boting.guo
 * @date 2020/11/10 16:58
 */

@Data
@AllArgsConstructor
public class LoginVO {
    @NonNull
    private String id;
    @NonNull
    private String password;

    @Override
    public String toString() {
        return "LoginVO{" + id  + ", " + password + "}";
    }
}
