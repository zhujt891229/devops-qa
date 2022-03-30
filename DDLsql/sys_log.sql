-- test.sys_log definition

CREATE TABLE `sys_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `level` varchar(100) DEFAULT NULL,
  `req_url` varchar(100) DEFAULT NULL,
  `method` varchar(100) DEFAULT NULL,
  `param` varchar(100) DEFAULT NULL,
  `response` varchar(100) DEFAULT NULL,
  `total_time` varchar(100) DEFAULT NULL,
  `error_content` varchar(100) DEFAULT NULL,
  `operation` varchar(100) DEFAULT NULL,
  `client_ip` varchar(100) DEFAULT NULL,
  `operator_id` varchar(100) DEFAULT NULL,
  `create_time` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;