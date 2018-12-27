SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `task_status`
-- ----------------------------
DROP TABLE IF EXISTS `task_status`;
CREATE TABLE `task_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT b'1',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of task_status
-- ----------------------------
