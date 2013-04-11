-- 用户信息数据表
CREATE TABLE IF NOT EXISTS `oauth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `create_time` bigint(20) DEFAULT '0' COMMENT '创建时间戳',
  `update_time` bigint(20) DEFAULT '0' COMMENT '更新时间戳',
  `state` int(11) DEFAULT '1' COMMENT '默认为1，逻辑删除为0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 插入测试数据
INSERT INTO `oauth2`.`oauth_user` (`id`, `email`, `password`, `create_time`, `update_time`, `state`) VALUES (NULL, 'shuhuan2009@gmail.com', '123456', '0', '0', '1'), (NULL, 'shuhuan@happinesstree.com', '123456', '0', '0', '1');

-- 访问令牌数据表
CREATE TABLE IF NOT EXISTS `oauth_access_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `access_token` varchar(255) DEFAULT '' COMMENT 'access_token字符串',
  `token` blob COMMENT 'access_token对象字节流',
  `expiration` bigint(20) DEFAULT '0' COMMENT 'access_token过期时间戳',
  `refresh_token` varchar(255) DEFAULT '' COMMENT 'refresh_token字符串',
  `app_key` varchar(255) DEFAULT '' COMMENT '客户端AppKey',
  `uid` bigint(255) DEFAULT '0' COMMENT '系统用户uid',
  `create_time` bigint(20) DEFAULT '0' COMMENT '创建时间戳',
  `update_time` bigint(20) DEFAULT '0' COMMENT '更新时间戳',
  `state` int(11) DEFAULT '1' COMMENT '默认有效1，0为逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 授权码数据表
CREATE TABLE IF NOT EXISTS `oauth_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` varchar(255) DEFAULT '' COMMENT '授权码',
  `authorize_request` blob COMMENT '授权请求对象',
  `expiration` bigint(20) DEFAULT '0' COMMENT '授权码过期时间戳',
  `create_time` bigint(20) DEFAULT '0' COMMENT '创建时间戳',
  `update_time` bigint(20) DEFAULT '0' COMMENT '更新时间戳',
  `state` int(11) DEFAULT '1' COMMENT '默认为1，逻辑删除为0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 刷新令牌数据表
CREATE TABLE IF NOT EXISTS `oauth_refresh_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `refresh_token` varchar(255) DEFAULT '' COMMENT '刷新令牌refresh_token字符串',
  `token` blob COMMENT '刷新令牌refresh_token',
  `expiration` bigint(20) DEFAULT '0' COMMENT 'refresh_token过期时间戳',
  `create_time` bigint(20) DEFAULT '0' COMMENT '创建时间戳',
  `update_time` bigint(20) DEFAULT '0' COMMENT '更新戳时间戳',
  `state` int(11) DEFAULT '1' COMMENT '默认为1，逻辑删除状态为0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 应用App数据表
CREATE TABLE IF NOT EXISTS `app_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_key` char(32) NOT NULL,
  `app_secret` varchar(255) DEFAULT NULL,
  `app_name` varchar(255) NOT NULL COMMENT '应用名称',
  `app_type` int(10) NOT NULL COMMENT '应用类型. 1 PC客户端应用  2移动客户端应用 3 网站',
  `app_desc` varchar(255) DEFAULT NULL COMMENT '应用描述',
  `app_homepage` varchar(255) DEFAULT NULL COMMENT '应用主页',
  `app_tag` varchar(255) DEFAULT NULL COMMENT '应用标签：以空格隔开，搜索时用',
  `app_icon` varchar(255) DEFAULT NULL COMMENT '应用的图标链接',
  `owner_type` int(11) DEFAULT NULL COMMENT '申请人类别： 1 个人用户 2 企业用户',
  `owner_email` varchar(255) NOT NULL DEFAULT '' COMMENT '申请人邮箱',
  `owner_uid` bigint(20) NOT NULL COMMENT '申请人奇艺帐号uid',
  `owner_phone` varchar(255) DEFAULT NULL COMMENT '申请人联系电话',
  `owner_name` varchar(255) DEFAULT NULL COMMENT '申请人真实姓名',
  `owner_company_name` varchar(255) DEFAULT NULL COMMENT '申请人公司名称',
  `owner_address` varchar(255) DEFAULT NULL COMMENT '申请人联系地址',
  `create_time` bigint(20) DEFAULT '0' COMMENT '创建时间戳',
  `update_time` bigint(20) DEFAULT '0' COMMENT '更新戳时间戳',
  `authorized_grant_types` varchar(255) DEFAULT 'authorization_code,refresh_token,implicit' COMMENT '授权类型，默认authorization_code,refresh_token,implicit',
  `redirect_uri` varchar(255) DEFAULT NULL COMMENT 'APP重定向URL',
  `access_token_validity` int(11) DEFAULT '2592000' COMMENT '访问令牌有效时间s,默认为3个月',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '刷新令牌有效时间s',
  `state` int(11) DEFAULT '1' COMMENT '默认为1，逻辑删除状态为0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_app_name` (`app_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;