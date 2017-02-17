/*

Source Server         : root
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : seckill

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2017-02-17 12:05:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '库存数量',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '秒杀开始时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀结束时间',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1006 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES ('1000', '100元秒杀诺基亚', '10000', '2017-02-17 12:02:05', '2019-12-21 00:00:00', '2016-12-21 00:00:00');
INSERT INTO `seckill` VALUES ('1001', '5000元秒杀iphone7', '1700', '2017-02-17 11:47:36', '2016-04-11 00:00:00', '2016-04-11 00:00:00');
INSERT INTO `seckill` VALUES ('1002', '1000元秒杀ipad1', '3500', '2017-02-17 11:47:41', '2016-05-12 00:00:00', '2016-05-12 00:00:00');
INSERT INTO `seckill` VALUES ('1003', '1600元秒杀小米4', '1200', '2017-02-17 11:47:47', '2016-07-23 00:00:00', '2016-07-23 00:00:00');
INSERT INTO `seckill` VALUES ('1004', '1400元秒杀魅族4', '1099', '2017-02-17 12:02:28', '2018-02-17 11:48:00', '2017-02-17 11:48:00');
INSERT INTO `seckill` VALUES ('1005', '1400元秒杀小米3', '1100', '2017-02-17 11:48:23', '2016-01-21 00:00:00', '2016-01-21 00:00:00');

-- ----------------------------
-- Table structure for success_killed
-- ----------------------------
DROP TABLE IF EXISTS `success_killed`;
CREATE TABLE `success_killed` (
  `seckill_id` bigint(20) NOT NULL COMMENT '秒杀商品ID',
  `user_phone` bigint(20) NOT NULL COMMENT '用户手机号',
  `state` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '状态标识:-1:无效 0:成功 1:已付款 2:已发货',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`,`user_phone`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

-- ----------------------------
-- Records of success_killed
-- ----------------------------
INSERT INTO `success_killed` VALUES ('1004', '15643645806', '-1', '2017-02-17 12:02:27');

-- ----------------------------
-- View structure for v1
-- ----------------------------
DROP VIEW IF EXISTS `v1`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `v1` AS SELECT *FROM seckill ;

-- ----------------------------
-- Procedure structure for execute_seckill
-- ----------------------------
DROP PROCEDURE IF EXISTS `execute_seckill`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `execute_seckill`(IN v_seckill_id bigint, IN v_phone BIGINT,
IN v_kill_time TIMESTAMP, OUT r_result INT)
BEGIN
		DECLARE insert_count INT DEFAULT 0;
		START TRANSACTION;
		INSERT ignore INTO success_killed (seckill_id, user_phone, create_time)
		VALUES(v_seckill_id, v_phone, v_kill_time);
		SELECT ROW_COUNT() INTO insert_count;
		IF (insert_count = 0) THEN
			ROLLBACK;
			SET r_result = -1;
		ELSEIF (insert_count < 0) THEN
			ROLLBACK ;
			SET r_result = -2;
		ELSE
			UPDATE seckill SET number = number - 1
			WHERE seckill_id = v_seckill_id AND end_time > v_kill_time
			AND start_time < v_kill_time AND number > 0;
			SELECT ROW_COUNT() INTO insert_count;
			IF (insert_count = 0) THEN
				ROLLBACK;
				SET r_result = 0;
			ELSEIF (insert_count < 0) THEN
				ROLLBACK;
				SET r_result = -2;
			ELSE
				COMMIT;
			SET r_result = 1;
			END IF;
		END IF;
	END
;;
DELIMITER ;
SET FOREIGN_KEY_CHECKS=1;
