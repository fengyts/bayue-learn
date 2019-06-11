-- --------------------------------------------------------
-- 主机:                           47.94.199.26
-- 服务器版本:                        5.7.20 - MySQL Community Server (GPL)
-- 服务器操作系统:                      linux-glibc2.12
-- HeidiSQL 版本:                  10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 ygbi 的数据库结构
CREATE DATABASE IF NOT EXISTS `ygbi1` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ygbi1`;

-- 导出  表 ygbi.activity 结构
CREATE TABLE IF NOT EXISTS `activity` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `community_id` varchar(32) NOT NULL COMMENT '小区ID',
  `community_name` varchar(50) DEFAULT '' COMMENT '小区名称',
  `title` varchar(100) DEFAULT '' COMMENT '标题',
  `detail_url` varchar(200) DEFAULT '' COMMENT '详情链接',
  `address` varchar(200) DEFAULT '' COMMENT '地址',
  `price` varchar(200) DEFAULT '' COMMENT '价格，多个用逗号分隔',
  `gmt_start` datetime DEFAULT NULL COMMENT '开始时间',
  `gmt_end` datetime DEFAULT NULL COMMENT '结束时间',
  `is_deleted` char(1) DEFAULT '0' COMMENT '是否删除，1：是；0：否。',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_community_id` (`community_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.admin 结构
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `account` varchar(16) NOT NULL COMMENT '账号',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `state` char(1) NOT NULL DEFAULT '0' COMMENT '状态，0：正常；1：冻结。',
  `is_deleted` char(1) DEFAULT '0' COMMENT '是否删除，1：是；0：否。',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.admin_menu 结构
CREATE TABLE IF NOT EXISTS `admin_menu` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `pid` int(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(30) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `type` char(1) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` char(1) DEFAULT '0' COMMENT '是否删除，1：是；0：否。',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- 数据导出被取消选择。
-- 导出  表 ygbi.building 结构
CREATE TABLE IF NOT EXISTS `building` (
  `id` varchar(32) NOT NULL COMMENT 'ID，与物业系统一致',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `community_id` varchar(32) NOT NULL COMMENT '小区ID',
  `community_name` varchar(50) NOT NULL DEFAULT '' COMMENT '小区名称',
  `unit_price` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '房价，元/㎡',
  `is_deleted` char(1) DEFAULT '0' COMMENT '是否删除，1：是；0：否。',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_community_id` (`community_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='楼表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.community 结构
CREATE TABLE IF NOT EXISTS `community` (
  `id` varchar(32) NOT NULL COMMENT 'ID，与阳光社区系统一致',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `level` int(11) NOT NULL DEFAULT '5' COMMENT '等级，1~5',
  `tcw_up_price` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '地上停车位价格',
  `tcw_down_price` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '地下停车位价格',
  `is_deleted` char(1) DEFAULT '0' COMMENT '是否删除，1：是；0：否。',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小区表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.community_bill_statistics 结构
CREATE TABLE IF NOT EXISTS `community_bill_statistics` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `community_id` varchar(32) DEFAULT NULL COMMENT '小区ID',
  `community_name` varchar(50) DEFAULT '' COMMENT '小区名称',
  `date` char(6) DEFAULT NULL COMMENT '日期，格式：yyyyMM',
  `property_fee_amt` decimal(20,2) DEFAULT '0.00' COMMENT '物业费总金额',
  `property_fee_paid_amt` decimal(20,2) DEFAULT '0.00' COMMENT '物业费已交金额',
  `parking_fee_amt` decimal(20,2) DEFAULT '0.00' COMMENT '停车费总金额',
  `parking_fee_paid_amt` decimal(20,2) DEFAULT '0.00' COMMENT '停车费已交金额',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_community_id` (`community_id`),
  KEY `idx_date` (`date`)
) ENGINE=InnoDB AUTO_INCREMENT=394 DEFAULT CHARSET=utf8 COMMENT='小区账单统计表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.complaint 结构
CREATE TABLE IF NOT EXISTS `complaint` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `house_id` varchar(32) NOT NULL COMMENT '户ID',
  `house_name` varchar(32) DEFAULT NULL COMMENT '户名称',
  `building_id` varchar(32) DEFAULT NULL COMMENT '楼ID',
  `building_name` varchar(50) DEFAULT '' COMMENT '楼名称',
  `community_id` varchar(32) DEFAULT NULL COMMENT '小区ID',
  `community_name` varchar(50) DEFAULT '' COMMENT '小区名称',
  `content` varchar(2000) DEFAULT '' COMMENT '内容',
  `gmt_complaint` datetime DEFAULT NULL COMMENT '投诉时间',
  `is_deleted` char(1) DEFAULT '0' COMMENT '是否删除，1：是；0：否。',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_house_id` (`house_id`) USING BTREE,
  KEY `idx_community_id` (`community_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投诉表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.house 结构
CREATE TABLE IF NOT EXISTS `house` (
  `id` varchar(32) NOT NULL COMMENT 'ID，与物业系统一致',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `building_id` varchar(32) NOT NULL COMMENT '楼ID',
  `building_name` varchar(50) NOT NULL DEFAULT '' COMMENT '楼名称',
  `community_id` varchar(32) NOT NULL COMMENT '小区ID',
  `community_name` varchar(50) NOT NULL DEFAULT '' COMMENT '小区名称',
  `model` varchar(50) NOT NULL DEFAULT '' COMMENT '户型',
  `area` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '面积，㎡',
  `score` char(1) NOT NULL DEFAULT '' COMMENT '评分，枚举值：S、A、B、C。',
  `is_deleted` char(1) DEFAULT '0' COMMENT '是否删除，1：是；0：否。',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_building_id` (`building_id`) USING BTREE,
  KEY `idx_community_id` (`community_id`) USING BTREE,
  KEY `idx_score` (`score`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='户表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.investment 结构
CREATE TABLE IF NOT EXISTS `investment` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `house_id` varchar(32) NOT NULL COMMENT '户ID',
  `house_name` varchar(32) DEFAULT NULL COMMENT '户名称',
  `building_id` varchar(32) DEFAULT NULL COMMENT '楼ID',
  `building_name` varchar(50) DEFAULT '' COMMENT '楼名称',
  `community_id` varchar(32) DEFAULT NULL COMMENT '小区ID',
  `community_name` varchar(50) DEFAULT '' COMMENT '小区名称',
  `product_name` varchar(50) DEFAULT '' COMMENT '产品名称',
  `invest_amount` decimal(20,2) DEFAULT '0.00' COMMENT '投资金额',
  `gmt_invest` datetime DEFAULT NULL COMMENT '投资时间',
  `is_deleted` char(1) DEFAULT '0' COMMENT '是否删除，1：是；0：否。',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_community_id` (`community_id`),
  KEY `idx_house_id` (`house_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投资表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.notice 结构
CREATE TABLE IF NOT EXISTS `notice` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `community_id` varchar(32) NOT NULL COMMENT '小区ID',
  `community_name` varchar(50) DEFAULT '' COMMENT '小区名称',
  `title` varchar(100) DEFAULT '' COMMENT '标题',
  `publisher` varchar(50) DEFAULT NULL COMMENT '发布人',
  `gmt_publish` datetime DEFAULT NULL COMMENT '发布时间',
  `is_deleted` char(1) DEFAULT '0' COMMENT '是否删除，1：是；0：否。',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_community_id` (`community_id`),
  KEY `idx_gmt_publish` (`gmt_publish`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知公告表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.notice_content 结构
CREATE TABLE IF NOT EXISTS `notice_content` (
  `notice_id` varchar(32) NOT NULL COMMENT '通知公告ID',
  `content` text COMMENT '内容',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知公告内容表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.parking_fee_bill 结构
CREATE TABLE IF NOT EXISTS `parking_fee_bill` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `house_id` varchar(32) NOT NULL COMMENT '户ID',
  `house_name` varchar(32) DEFAULT NULL COMMENT '户名称',
  `building_id` varchar(32) DEFAULT NULL COMMENT '楼ID',
  `building_name` varchar(50) DEFAULT '' COMMENT '楼名称',
  `community_id` varchar(32) DEFAULT NULL COMMENT '小区ID',
  `community_name` varchar(50) DEFAULT '' COMMENT '小区名称',
  `date` char(6) DEFAULT NULL COMMENT '日期，格式：yyyyMM',
  `amount` decimal(20,2) DEFAULT '0.00' COMMENT '价格',
  `is_paid` char(1) DEFAULT '0' COMMENT '是否支付，1：是；0：否。',
  `gmt_pay` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_info` varchar(64) DEFAULT NULL COMMENT '支付信息',
  `is_deleted` char(1) DEFAULT '0' COMMENT '是否删除，1：是；0：否。',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_house_id` (`house_id`) USING BTREE,
  KEY `idx_community_id` (`community_id`) USING BTREE,
  KEY `idx_date` (`date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车费账单表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.parking_place 结构
CREATE TABLE IF NOT EXISTS `parking_place` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `house_id` varchar(32) NOT NULL COMMENT '户ID',
  `house_name` varchar(32) DEFAULT NULL COMMENT '户名称',
  `building_id` varchar(32) DEFAULT NULL COMMENT '楼ID',
  `building_name` varchar(50) DEFAULT '' COMMENT '楼名称',
  `community_id` varchar(32) DEFAULT NULL COMMENT '小区ID',
  `community_name` varchar(50) DEFAULT '' COMMENT '小区名称',
  `num` varchar(16) DEFAULT NULL COMMENT '车位号',
  `up_down` char(1) DEFAULT '0' COMMENT '地上地下，0：地上；1：地下。',
  `is_deleted` char(1) DEFAULT '0' COMMENT '是否删除，1：是；0：否。',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_house_id` (`house_id`) USING BTREE,
  KEY `idx_community_id` (`community_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车位表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.pay_order 结构
CREATE TABLE IF NOT EXISTS `pay_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `house_id` varchar(32) NOT NULL COMMENT '户ID',
  `house_name` varchar(32) DEFAULT NULL COMMENT '户名称',
  `building_id` varchar(32) DEFAULT NULL COMMENT '楼ID',
  `building_name` varchar(50) DEFAULT '' COMMENT '楼名称',
  `community_id` varchar(32) DEFAULT NULL COMMENT '小区ID',
  `community_name` varchar(50) DEFAULT '' COMMENT '小区名称',
  `type` char(1) DEFAULT '9' COMMENT '类型，枚举值：0：水电煤；9：其他。',
  `order_no` varchar(32) DEFAULT '' COMMENT '订单号',
  `amount` decimal(20,2) DEFAULT '0.00' COMMENT '金额',
  `gmt_order` datetime DEFAULT NULL COMMENT '下单时间',
  `is_deleted` char(1) DEFAULT '0' COMMENT '是否删除，1：是；0：否。',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_community_id` (`community_id`),
  KEY `idx_house_id` (`house_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='支付订单表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.property_fee_bill 结构
CREATE TABLE IF NOT EXISTS `property_fee_bill` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `house_id` varchar(32) NOT NULL COMMENT '户ID',
  `house_name` varchar(32) DEFAULT NULL COMMENT '户名称',
  `building_id` varchar(32) DEFAULT NULL COMMENT '楼ID',
  `building_name` varchar(50) DEFAULT '' COMMENT '楼名称',
  `community_id` varchar(32) DEFAULT NULL COMMENT '小区ID',
  `community_name` varchar(50) DEFAULT '' COMMENT '小区名称',
  `date` char(6) DEFAULT NULL COMMENT '日期，格式：yyyyMM',
  `amount` decimal(20,2) DEFAULT '0.00' COMMENT '价格',
  `is_paid` char(1) DEFAULT '0' COMMENT '是否支付，1：是；0：否。',
  `gmt_pay` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_info` varchar(64) DEFAULT NULL COMMENT '支付信息',
  `is_deleted` char(1) DEFAULT '0' COMMENT '是否删除，1：是；0：否。',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_date` (`date`),
  KEY `idx_house_id` (`house_id`) USING BTREE,
  KEY `idx_community_id` (`community_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物业费账单表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.rel_resident_house 结构
CREATE TABLE IF NOT EXISTS `rel_resident_house` (
  `resident_id` varchar(32) NOT NULL COMMENT '居民ID',
  `house_id` varchar(32) NOT NULL COMMENT '户ID',
  `building_id` varchar(32) DEFAULT NULL COMMENT '楼ID',
  `community_id` varchar(32) DEFAULT NULL COMMENT '小区ID',
  `role` char(1) DEFAULT '9' COMMENT '角色，枚举值：0：业主，1：家属，2：租客，9：未设置。',
  PRIMARY KEY (`resident_id`,`house_id`),
  UNIQUE KEY `uk_resident_community_id` (`resident_id`,`community_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='居民与户关系表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.repair 结构
CREATE TABLE IF NOT EXISTS `repair` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `house_id` varchar(32) NOT NULL COMMENT '户ID',
  `house_name` varchar(32) DEFAULT NULL COMMENT '户名称',
  `building_id` varchar(32) DEFAULT NULL COMMENT '楼ID',
  `building_name` varchar(50) DEFAULT '' COMMENT '楼名称',
  `community_id` varchar(32) DEFAULT NULL COMMENT '小区ID',
  `community_name` varchar(50) DEFAULT '' COMMENT '小区名称',
  `category` char(1) DEFAULT '0' COMMENT '种类，枚举值：0：公共报修；1：居家报修。',
  `content` varchar(2000) DEFAULT '' COMMENT '内容',
  `gmt_repair` datetime DEFAULT NULL COMMENT '报修时间',
  `is_deleted` char(1) DEFAULT '0' COMMENT '是否删除，1：是；0：否。',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_community_id` (`community_id`),
  KEY `idx_house_id` (`house_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报修表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.resident 结构
CREATE TABLE IF NOT EXISTS `resident` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(50) DEFAULT '' COMMENT '名称',
  `mobile` char(11) DEFAULT NULL COMMENT '手机号',
  `gender` char(1) DEFAULT '0' COMMENT '性别，枚举值：0：未知；1：男；2：女',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `gmt_register` datetime DEFAULT NULL COMMENT '注册时间',
  `is_deleted` char(1) DEFAULT '0' COMMENT '是否删除，1：是；0：否。',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='居民表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `username` varchar(16) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `state` char(1) DEFAULT '0' COMMENT '状态，0：正常；1：冻结。',
  `is_deleted` char(1) DEFAULT '0' COMMENT '是否删除，1：是；0：否。',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.user_token 结构
CREATE TABLE IF NOT EXISTS `user_token` (
  `user_id` int(10) unsigned NOT NULL COMMENT '用户ID',
  `token` varchar(100) NOT NULL COMMENT 'Token',
  `gmt_expire` datetime DEFAULT NULL COMMENT '过期时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户Token表';

-- 数据导出被取消选择。
-- 导出  表 ygbi.vehicle 结构
CREATE TABLE IF NOT EXISTS `vehicle` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `house_id` varchar(32) NOT NULL COMMENT '户ID',
  `house_name` varchar(32) DEFAULT NULL COMMENT '户名称',
  `building_id` varchar(32) DEFAULT NULL COMMENT '楼ID',
  `building_name` varchar(50) DEFAULT '' COMMENT '楼名称',
  `community_id` varchar(32) DEFAULT NULL COMMENT '小区ID',
  `community_name` varchar(50) DEFAULT '' COMMENT '小区名称',
  `plate_num` varchar(16) DEFAULT NULL COMMENT '车牌号',
  `description` varchar(64) DEFAULT NULL COMMENT '描述',
  `price` decimal(20,2) DEFAULT '0.00' COMMENT '价格',
  `is_deleted` char(1) DEFAULT '0' COMMENT '是否删除，1：是；0：否。',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_community_id` (`community_id`) USING BTREE,
  KEY `idx_house_id` (`house_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆表';

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
