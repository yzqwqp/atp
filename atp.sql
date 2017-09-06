/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.95
Source Server Version : 50626
Source Host           : 192.168.10.95:3306
Source Database       : atp2

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-08-31 17:09:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for init_consumer
-- ----------------------------
DROP TABLE IF EXISTS `init_consumer`;
CREATE TABLE `init_consumer` (
  `servername` varchar(255) DEFAULT NULL,
  `interfacename` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `generic` varchar(255) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `consumerid` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`consumerid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for init_field
-- ----------------------------
DROP TABLE IF EXISTS `init_field`;
CREATE TABLE `init_field` (
  `para_id` int(11) DEFAULT NULL,
  `field_no` int(11) DEFAULT NULL,
  `field_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `field_type` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for init_para
-- ----------------------------
DROP TABLE IF EXISTS `init_para`;
CREATE TABLE `init_para` (
  `para_id` int(11) NOT NULL AUTO_INCREMENT,
  `method_id` int(11) DEFAULT NULL,
  `para_name` varchar(255) DEFAULT NULL,
  `para_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`para_id`)
) ENGINE=InnoDB AUTO_INCREMENT=785 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for init_service
-- ----------------------------
DROP TABLE IF EXISTS `init_service`;
CREATE TABLE `init_service` (
  `method_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_name` varchar(255) DEFAULT NULL,
  `method_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`method_id`)
) ENGINE=InnoDB AUTO_INCREMENT=558 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for test_case
-- ----------------------------
DROP TABLE IF EXISTS `test_case`;
CREATE TABLE `test_case` (
  `method_id` int(11) NOT NULL,
  `case_id` int(11) NOT NULL AUTO_INCREMENT,
  `case_des` varchar(255) NOT NULL,
  `case_data` varchar(1024) DEFAULT NULL,
  `case_assert_type` varchar(255) DEFAULT NULL,
  `case_assert_value` varchar(255) DEFAULT NULL,
  `is_run` int(1) NOT NULL DEFAULT '0',
  `is_del` int(1) NOT NULL,
  PRIMARY KEY (`case_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for test_data
-- ----------------------------
DROP TABLE IF EXISTS `test_data`;
CREATE TABLE `test_data` (
  `case_id` int(11) NOT NULL,
  `data_id` int(11) NOT NULL AUTO_INCREMENT,
  `para_name` varchar(255) DEFAULT NULL,
  `para_type` varchar(255) DEFAULT NULL,
  `field_name` varchar(255) DEFAULT NULL,
  `field_type` varchar(255) DEFAULT NULL,
  `value_data` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for test_method
-- ----------------------------
DROP TABLE IF EXISTS `test_method`;
CREATE TABLE `test_method` (
  `service_id` int(11) NOT NULL,
  `method_id` int(11) NOT NULL AUTO_INCREMENT,
  `method_name` varchar(255) NOT NULL,
  `method_des` varchar(255) DEFAULT NULL,
  `is_run` int(1) NOT NULL,
  `is_del` int(1) NOT NULL,
  PRIMARY KEY (`method_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for test_report
-- ----------------------------
DROP TABLE IF EXISTS `test_report`;
CREATE TABLE `test_report` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_id` int(11) DEFAULT NULL,
  `method_id` int(11) DEFAULT NULL,
  `data_id` int(11) DEFAULT NULL,
  `case_id` int(11) DEFAULT NULL,
  `report_data` varchar(8192) DEFAULT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for test_service
-- ----------------------------
DROP TABLE IF EXISTS `test_service`;
CREATE TABLE `test_service` (
  `service_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_name` varchar(255) NOT NULL,
  `service_des` varchar(255) DEFAULT NULL,
  `is_run` int(1) NOT NULL,
  `is_del` int(1) NOT NULL,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;
