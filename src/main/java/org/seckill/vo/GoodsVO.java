package org.seckill.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.seckill.model.Goods;

@Data
@TableName("goods")
public class GoodsVO extends Goods {
}
