package org.seckill.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.seckill.common.exception.ExceptionCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> {

    private String rstCode;
    private String rstMsg;
    private T rst;

    public static <T> CommonResponse<T> success(T rst) {
        return new CommonResponse<T>(ExceptionCode.EX_SUCCESS, "Success", rst);
    }
}
