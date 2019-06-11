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
CREATE DATABASE IF NOT EXISTS `ygbi` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ygbi`;

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
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
