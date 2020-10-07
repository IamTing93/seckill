create database if not exists `seckill`;

use `seckill`;

drop table if exists `goods`;
create table `goods` (
	`id` int(11) unsigned primary key auto_increment,
    `goods_name` varchar(128) default null,
    `goods_title` varchar(128) default null,
    `goods_img` varchar(256) default null,
    `goods_detail` longtext,
    `goods_price` decimal(10, 2),
    `goods_stock` int(11) default 0
);

drop table if exists `order_info`;
create table `order_info` (
	`id` int(11) unsigned primary key auto_increment,
    `user_id` int(11) unsigned not null,
    `goods_id` int(11) unsigned not null,
    `goods_name` varchar(128),
    `goods_count` int(11),
    `goods_price` decimal(10,2),
    `status` tinyint,
    `create_date` datetime,
    `pay_date` datetime
);

drop table if exists `seckill_goods`;
create table `seckill_goods` (
	`id` int(11) unsigned primary key auto_increment,
    `goods_id` int(11) unsigned not null,
    `seckill_price` decimal(10, 2),
    `seckill_stock` int(11),
    `start_time` datetime,
    `end_time` datetime
);

drop table if exists `seckill_order`;
create table `seckill_order` (
	`id` int(11) unsigned primary key auto_increment,
    `user_id` int(11) unsigned not null,
    `goods_id` int(11) unsigned not null,
    `order_id` int(11) unsigned not null
);

drop table if exists `user`;
create table `user` (
	`id` int(11) unsigned primary key auto_increment,
    `name` varchar(64) default null,
    `password` varchar(128) default null,
    `salt` varchar(32) default null,
    `head_url` varchar(256) default null
);