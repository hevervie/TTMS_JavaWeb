/*==============================================================*/
/* DBMS name:      MySQL 5.x                                    */
/* Created on:     2016/6/13 15:40:40                           */
/*==============================================================*/
DROP DATABASE IF EXISTS ttms_web;
CREATE DATABASE ttms_web;
use ttms_web;

DROP TABLE IF EXISTS generalmanager;   /*总经理*/
DROP TABLE IF EXISTS manager;          /*经理*/
DROP TABLE IF EXISTS employee;         /*售票员*/
DROP TABLE IF EXISTS customer;         /*顾客*/
DROP TABLE IF EXISTS theater;          /*剧院*/
DROP TABLE IF EXISTS studio;           /*演出厅*/
DROP TABLE IF EXISTS seat;             /*座位*/
DROP TABLE IF EXISTS ticket;           /*票*/
DROP TABLE IF EXISTS bill;             /*账单*/
DROP TABLE IF EXISTS play;             /*剧目*/
DROP TABLE IF EXISTS schedule;         /*演出计划*/
DROP TABLE IF EXISTS type;             /*剧目类型*/
DROP TABLE IF EXISTS lang;             /*剧目语言*/
DROP TABLE IF EXISTS level;            /*剧目等级*/

/*********************************************************************************************************/
/*==============================================================*/
/* Table: generalmanager                                        */
/*==============================================================*/
CREATE TABLE generalmanager(
  id                INT            AUTO_INCREMENT,
  emp_no            CHAR(8)        NOT NULL ,                #工号 总经理由1开头，经理2开头，售票员3开头
  name              VARCHAR(40),                             #总经理姓名
  passwd            VARCHAR(20)    DEFAULT '000000',         #总经理密码，初始密码000000
  PRIMARY KEY (id)
);

INSERT into generalmanager VALUES (1,'10000001','Boss','123456');

/*********************************************************************************************************/
/*==============================================================*/
/* Table: manager     剧院经理                                   */
/*==============================================================*/
CREATE TABLE manager(
  id                INT            AUTO_INCREMENT,
  emp_no            CHAR(8)        NOT NULL,            #工号 总经理由1开头，经理2开头，售票员3开头
  theater_id        INT            NOT NULL,            #剧院id
  name              VARCHAR(40),                        #经理姓名，初始秘密000000
  passwd            VARCHAR(20)    DEFAULT '000000',    #经理密码
  PRIMARY KEY (id)
);

INSERT into manager VALUES (1,'20000001',1,'Box','123456');
INSERT into manager VALUES (2,'20000002',1,'Girl','123456');

/*********************************************************************************************************/

/*==============================================================*/
/* Table: employee     售票员                                   */
/*==============================================================*/
CREATE TABLE employee(
  id                INT           AUTO_INCREMENT,
  emp_no            CHAR(8)       NOT NULL,         #工号 总经理由1开头，经理2开头，售票员3开头
  theater_id        INT           NOT NULL,         #剧院id
  name              VARCHAR(40),                    #售票员姓名
  passwd            VARCHAR(20)   DEFAULT '000000', #售票员密码,初始密码000000
  tel               CHAR(11),                        #售票员手机号码
  PRIMARY KEY (id)
);

INSERT into employee VALUES (1,'30000001',1,'employ1','123456','12345678901');

INSERT into employee VALUES (2,'30000002',2,'employ2','123456','12345678902');

/*********************************************************************************************************/


/*==============================================================*/
/* Table: customer  顾客                                        */
/*==============================================================*/

CREATE TABLE customer(
  id                INT           AUTO_INCREMENT,
  name              VARCHAR(40),                  #顾客姓名
  tel               BIGINT(11)      NOT NULL ,         #顾客电话号码
  PRIMARY KEY (id)
);

INSERT INTO customer VALUES (1,"Customer1",12345678903);
INSERT INTO customer VALUES (2,"Customer2",12345678904);
/*********************************************************************************************************/
/*==============================================================*/
/* Table: theater   剧院                                         */
/*==============================================================*/
CREATE TABLE theater(
  id              INT            AUTO_INCREMENT,
  name            VARCHAR(40)    NOT NULL ,        #影院名
  studio_number   SMALLINT       NOT NULL ,        #影厅数量
  addr            VARCHAR(50)    NOT NULL ,        #剧院地址
  PRIMARY KEY (id)
);

INSERT into theater VALUES (1,'NO.1Theater',2,'Street1');
INSERT into theater VALUES (2,'NO.2Theater',2,'Street2');

/*********************************************************************************************************/
/*==============================================================*/
/* Table: studio  演出厅                                         */
/*==============================================================*/
CREATE TABLE studio(
  id              INT          AUTO_INCREMENT,
  theater_id      INT          NOT NULL ,         #剧院id
  name            VARCHAR(40)  NOT NULL ,         #演出厅名称
  row             SMALLINT     NOT NULL ,         #演出厅列数
  col             SMALLINT     NOT NULL ,         #演出厅行数
  PRIMARY KEY (id)
);

INSERT INTO  studio VALUES (1,1,"NO.1Theater.NO.1Studio",2,2);
INSERT INTO  studio VALUES (2,1,"NO.1Theater.NO.2Studio",3,2);
INSERT INTO  studio VALUES (3,2,"NO.2Theater.NO.1Studio",3,2);
INSERT INTO  studio VALUES (4,2,"NO.2Theater.NO.2Studio",2,2);


/*********************************************************************************************************/


/*==============================================================*/
/* Table:  seat 座位                                            */
/*==============================================================*/
CREATE TABLE seat(
  id             INT          AUTO_INCREMENT,
  studio_id      INT          NOT NULL ,              #演出厅id
  row            SMALLINT     NOT NULL ,              #座位所在行
  col            SMALLINT     NOT NULL ,              #座位所在列
  status         TINYINT      DEFAULT 1,              #座位状态  0：损坏 1：可用
  PRIMARY KEY (id)
);

INSERT INTO seat VALUES (1,1,1,1,1);
INSERT INTO seat VALUES (2,1,1,2,1);
INSERT INTO seat VALUES (3,1,2,1,1);
INSERT INTO seat VALUES (4,1,2,2,1);

INSERT INTO seat VALUES (5,2,1,1,1);
INSERT INTO seat VALUES (6,2,1,2,1);
INSERT INTO seat VALUES (7,2,2,1,1);
INSERT INTO seat VALUES (8,2,2,2,1);
INSERT INTO seat VALUES (9,2,3,1,1);
INSERT INTO seat VALUES (10,2,3,2,1);


INSERT INTO seat VALUES (11,3,1,1,1);
INSERT INTO seat VALUES (12,3,1,2,1);
INSERT INTO seat VALUES (13,3,2,1,1);
INSERT INTO seat VALUES (14,3,2,2,1);
INSERT INTO seat VALUES (15,3,3,1,1);
INSERT INTO seat VALUES (16,3,3,2,1);

INSERT INTO seat VALUES (17,4,1,1,1);
INSERT INTO seat VALUES (18,4,1,2,1);
INSERT INTO seat VALUES (19,4,2,1,1);
INSERT INTO seat VALUES (20,4,2,2,1);


/*********************************************************************************************************/

/*==============================================================*/
/* Table:  type 剧目类型                                         */
/*==============================================================*/
CREATE TABLE type(
  id           INT           AUTO_INCREMENT,
  type         VARCHAR(20)   NOT NULL ,         # 爱情，动作，剧情等
  PRIMARY KEY (id)
);

INSERT INTO type VALUES (1,"爱情");
INSERT INTO type VALUES (2,"动作");

/*********************************************************************************************************/


/*==============================================================*/
/* Table:  lang 剧目语言                                         */
/*==============================================================*/
CREATE TABLE lang(
  id           INT          AUTO_INCREMENT,
  type         VARCHAR(20)  NOT NULL ,         # 英语，法语，粤语，汉语，日语，韩语等
  PRIMARY KEY (id)
);

INSERT INTO lang VALUES (1,"英语");
INSERT INTO lang VALUES (2,"汉语");

/*********************************************************************************************************/


/*==============================================================*/
/* Table:  level 剧目等级                                        */
/*==============================================================*/
CREATE TABLE level(
  id           INT         AUTO_INCREMENT,
  type         VARCHAR(20) NOT NULL ,         # 2D 3D IMAX
  PRIMARY KEY (id)
);

INSERT INTO level VALUES (1,"2D");
INSERT INTO level VALUES (2,"3D");


/*********************************************************************************************************/

/*==============================================================*/
/* Table:  play 剧目                                            */
/*==============================================================*/
CREATE TABLE play(
  id            INT           AUTO_INCREMENT,
  name          VARCHAR(40)   NOT NULL ,      #剧名
  type_id       INT           NOT NULL ,      #剧目类型id
  lang_id       INT           NOT NULL ,      #剧目语言id
  level_id      INT           NOT NULL ,      #剧目等级id
  score         FLOAT       NOT NULL ,      #评分
  introd  VARCHAR(1000) NOT NULL ,      #剧目简介
  image_url     VARCHAR(100)  NOT NULL ,      #剧目图片url
  length        SMALLINT      NOT NULL ,      #剧目长度
  price         NUMERIC(10,2) NOT NULL ,      #票价
  status        TINYINT       DEFAULT 0,      #状态 0:待安排 1：已安排 -1：下线
  PRIMARY KEY (id)
 );

INSERT INTO play VALUES (1,"天空之间",1,1,1,8.0,"英国情报追捕一名女性","/home/",100,10,0);
INSERT INTO play VALUES (2,"午夜逃亡",1,1,2,6.0,"余真因怀孕和前男友反目而出走来到此地","/home/",30,30,0);
INSERT INTO play VALUES (3,"梦想合伙人",1,2,1,3.0,"无依无靠之时遂心生妙计","/home/",50,50,0);
INSERT INTO play VALUES (4,"劫机惊破",1,2,2,9.0,"邀查理和阿炳来到家中共度晚餐","/home/",70,70,0);
INSERT INTO play VALUES (5,"Good Tack",2,1,1,4.0,"人误以为与她促成好事导致怀孕","/home/",90,90,0);
INSERT INTO play VALUES (6,"伦敦沦陷",2,1,2,6.0,"难辨谁为亲生父亲，于是共同照顾余真","/home/",120,110,0);
INSERT INTO play VALUES (7,"犯罪家族",2,2,1,3.0,"事成后，二人偶然得知余真前男友想拿孩子做实验","/home/",140,130,0);
INSERT INTO play VALUES (8,"老炮儿",2,2,2,1.0,"于是查理和阿炳两人念及旧情","/home/",160,150,0);


/*********************************************************************************************************/

/*==============================================================*/
/* Table:  schedule 演出计划                                     */
/*==============================================================*/

CREATE TABLE schedule(
  id            INT           AUTO_INCREMENT,
  studio_id     INT           NOT NULL ,      #演出厅id
  play_id       INT           NOT NULL ,      #剧目id
  time          CHAR(14)      NOT NULL ,      #放映时间
  discount      NUMERIC(2,1) ,                #折扣
  price         NUMERIC(10,2) NOT NULL ,      #票价
  status        tinyint       not null ,      #演出计划状态,1 未过期 0 过期
  PRIMARY KEY (id)
);

INSERT INTO schedule VALUES (1,1,1,'20160616090000',0.8,8,1);

INSERT INTO schedule VALUES (2,4,2,'20160616180000',0.7,21,1);

/*********************************************************************************************************/

/*
INSERT INTO schedule VALUES (3,1,3,'20160617090000',0.6,30,1);
INSERT INTO schedule VALUES (4,2,4,'20160617180000',0.5,35,1);
INSERT INTO schedule VALUES (5,2,5,'20160618090000',0.4,36,1);
INSERT INTO schedule VALUES (6,2,6,'20160618180000',0.4,44,1);
INSERT INTO schedule VALUES (7,3,7,'20160619090000',0.5,65,1);
INSERT INTO schedule VALUES (8,3,8,'20160619180000',0.6,80,1);
INSERT INTO schedule VALUES (9,3,1,'20160620090000',0.7,7,1);
INSERT INTO schedule VALUES (10,4,2,'20160620180000',0.8,24,1);
INSERT INTO schedule VALUES (11,4,3,'20160621090000',0.9,45,1);
INSERT INTO schedule VALUES (12,4,4,'20160621180000',1.0,70,1);
*/
/*==============================================================*/
/* Table:  ticket 票                                            */
/*==============================================================*/

CREATE TABLE ticket(
  id           INT           AUTO_INCREMENT,
  seat_id      INT           NOT NULL ,               #座位id
  schedule_id  INT           NOT NULL ,               #演出计划id
  play_id      INT           NOT NULL ,               #剧目类型id
  price        NUMERIC(10,2) NOT NULL ,               #票价
  status       TINYINT       DEFAULT 0,               #票的状态 0：待售 1：锁定 2：卖出
  locktime     CHAR(14)      ,  #锁票时间2016年6月13号18点23分4秒 保存为：20160613182304
  PRIMARY KEY (id)
);

INSERT INTO ticket VALUES (1,1,1,1,8,0,NULL );
INSERT INTO ticket VALUES (2,2,1,1,8,2,NULL );
INSERT INTO ticket VALUES (3,3,1,1,8,0,NULL );
INSERT INTO ticket VALUES (4,4,1,1,8,0,NULL );

INSERT INTO ticket VALUES (5,1,2,4,21,0,NULL );
INSERT INTO ticket VALUES (6,2,2,4,21,0,NULL );
INSERT INTO ticket VALUES (7,3,2,4,21,2,NULL );


INSERT INTO ticket VALUES (8,4,2,4,21,0,NULL );

/*********************************************************************************************************/


/*==============================================================*/
/* Table:  bill 账单                                            */
/*==============================================================*/

CREATE TABLE bill(
  id          INT           AUTO_INCREMENT,
  customer_id INT           NOT NULL ,                #顾客id
  ticket_id   INT           NOT NULL ,                #票id
  emp_id      INT           NOT NULL ,                #售票员id
  play_id      INT           NOT NULL ,               #剧目id
  price        NUMERIC(10,2) NOT NULL ,               #票价
  sale_time   CHAR(14)       NULL ,   #售票时间2016年6月13号18点23分4秒 保存为：20160613182304
  PRIMARY KEY (id)
);
INSERT INTO bill VALUES (1,1,2,1,1,8,"20160615090000");
INSERT INTO bill VALUES (2,2,7,2,4,21,"20160612090000");
/*********************************************************************************************************/
