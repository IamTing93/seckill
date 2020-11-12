package com.seckill.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeckillStatus {
    public static final int NOT_BEGIN = 0;
    public static final int UNDER_SECKILLING = 1;
    public static final int END = 2;

    private int status;
    private long remainingSec;
}
