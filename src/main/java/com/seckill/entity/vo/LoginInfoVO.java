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
public class LoginInfoVO {
    @NonNull
    private String mobile;
    @NonNull
    private String password;

    @Override
    public String toString() {
        return "LoginVO{" + mobile + ", " + password + "}";
    }
}
