#mysql -u root -h loclhost -p 
#DROP TABLE tb_user ;
CREATE DATABASE shuerblog;
USE shuerblog;

CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `nickname` varchar(100) NOT NULL COMMENT '昵称',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `sign` varchar(100) DEFAULT NULL COMMENT '签名',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `background` varchar(100) DEFAULT NULL COMMENT '背景',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='用户表';

CREATE TABLE `tb_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `title_pic` varchar(200) DEFAULT NULL COMMENT '封面图片',
  `author` varchar(100) NOT NULL COMMENT '作者',
  `content` mediumtext COMMENT '内容',
  `state` varchar(100) NOT NULL COMMENT '状态',
  `eye_count` bigint(20) DEFAULT '0' COMMENT '浏览量',
  `publish_time` timestamp NOT NULL DEFAULT '1970-02-01 00:00:01' COMMENT '发布时间',
  `edit_time` timestamp NOT NULL DEFAULT '1970-02-01 00:00:01' COMMENT '上次修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='文章表';

CREATE TABLE `tb_tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '标签名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='标签表';

CREATE TABLE `tb_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='分类表';


CREATE TABLE `tb_article_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `category_id` bigint(20) NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='文章&&分类关联表';


CREATE TABLE `tb_article_tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `tags_id` bigint(20) NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='文章&&标签关联表';


