package org.seckill.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.seckill.common.exception.ExceptionCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {

    private String rstCode;
    private String rstMsg;
    private Object rst;

    public static CommonResponse success(Object rst) {
        return new CommonResponse(ExceptionCode.EX_SUCCESS, "Success", rst);
    }
}
