use seckill;

DROP TABLE IF EXISTS seckill_user;
CREATE TABLE seckill_user (
    id BIGINT(20) NOT NULL PRIMARY KEY COMMENT "用户ID，手机号码",
    nickname VARCHAR(255) NOT NULL COMMENT "昵称",
    password VARCHAR(32) DEFAULT NULL COMMENT "MD5(MD5(pass明文, 固定SALT), 随机SALT)",
    salt VARCHAR(10) DEFAULT NULL,
    head VARCHAR(256) DEFAULT NULL COMMENT "头像，云存储ID",
    register_date datetime DEFAULT NULL COMMENT "注册时间",
    last_login_date datetime DEFAULT NULL COMMENT "上一次登录时间",
    login_count int(11) DEFAULT '0' COMMENT "登录次数"
) ENGINE INNODB;

DROP TABLE IF EXISTS goods;
CREATE TABLE goods (
    id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT "商品id",
    goods_name VARCHAR(16) DEFAULT NULL COMMENT "商品名称",
    goods_title VARCHAR(64) DEFAULT NULL COMMENT "商品标题",
    goods_img VARCHAR(256) DEFAULT NULL COMMENT "商品图片",
    goods_detail LONGTEXT COMMENT "商品详情",
    goods_price DECIMAL(10, 2) DEFAULT '0.00' COMMENT "商品单价",
    goods_stock INT(11) DEFAULT 0 COMMENT "-1表示没有限制"
) ENGINE=INNODB AUTO_INCREMENT=3;

DROP TABLE IF EXISTS seckill_goods;
CREATE TABLE seckill_goods (
    seckill_id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT "秒杀商品id",
    goods_id BIGINT(20) DEFAULT NULL COMMENT "商品id",
    seckill_price DECIMAL(10, 2) DEFAULT '0.00' COMMENT "商品秒杀单价",
    stock_count INT(11) DEFAULT NULL COMMENT "库存数量",
    start_date DATETIME DEFAULT NULL COMMENT "秒杀开始时间",
    end_date DATETIME DEFAULT NULL COMMENT "秒杀结束时间"
) ENGINE=INNODB AUTO_INCREMENT=3;

INSERT INTO goods VALUES (1, "iPhoneX", "Apple iPhone X (A1865) 64GB 深空灰色 移动联通电信4G手机", "/img/iphonex.png", "Apple iPhone X (A1865) 64GB 深空灰色 移动联通电信4G手机【五月特惠】大屏性价比iPhone7Plus4199元，iPhone8低至3499元，iPhoneXR低至4799元！更多特价、优惠券，点此查看！选移动，享大流量，不换号购机！", 5999, 100);
INSERT INTO goods VALUES (2, "华为 P30", "华为 HUAWEI P30 Pro 超感光徕卡四摄10倍混合变焦麒麟980芯片屏内指纹", "/img/p30.png", "华为 HUAWEI P30 Pro 超感光徕卡四摄10倍混合变焦麒麟980芯片屏内指纹 8GB+256GB极光色全网通版双4G手机！", 5988, 55);

INSERT INTO seckill_goods VALUES (1, 1, 0.01, 4, "2020-11-10 14:48:16", "2020-12-11 14:48:16"), (2, 2, 0.01, 9, "2020-12-11 14:48:16", "2021-11-11 14:48:16");