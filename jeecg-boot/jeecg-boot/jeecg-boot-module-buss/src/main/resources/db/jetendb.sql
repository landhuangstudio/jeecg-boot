-- 创建mysql库
-- create database `jetendb` default character set utf8mb4 collate utf8mb4_general_ci;

DROP TABLE IF EXISTS t_bill;
CREATE TABLE t_bill (
  id char(32) NOT NULL COMMENT '主键ID',
  cust_name char(32) NOT NULL COMMENT '客户姓名',
  national_area TINYINT NOT NULL COMMENT '国家地区，1-西马、2-新加坡',
  express_no varchar(256) NOT NULL COMMENT '快递单号',
  cust_addr varchar(256) NOT NULL COMMENT '客户地址',
  contacts char(32) NOT NULL COMMENT '联系人',
  contacts_number char(16) NOT NULL COMMENT '联系电话',
  sensitive_goods TINYINT NOT NULL COMMENT '敏感货 0 否 1 是 ',
  weight DECIMAL(4,2) COMMENT '重量',
  volume_len_width_height char(16) COMMENT '体积长、宽、高，逗号分开',
  volume_weight DECIMAL(4,2) COMMENT '体积重（KG）',
  tracking_number char(16) COMMENT '运单号',
  deliver_company varchar(36) COMMENT '派送公司',
  freight_unit_price DECIMAL(4,2) COMMENT '运费单价',
  total_amount DECIMAL(10,2) COMMENT '运费',
  cust_pay_date char(19) COMMENT '客户付款时间,yyyy-MM-dd HH:mm:ss',
  cust_pay_channel char(10) COMMENT '客户付款渠道',
  cust_settlement TINYINT COMMENT '客户结算渠道，11-微信转账，12-微信红包，21-支付宝、31-马币',
  exchange_rate DECIMAL(4,2) COMMENT '马币汇率，两位小数',
  company_settlement TINYINT COMMENT '公司结算渠道，11-微信转账，12-微信红包，21-支付宝',
  company_settlement_date char(19) COMMENT '公司结算渠道日期,yyyy-MM-dd HH:mm:ss',
  remark varchar(128)  COMMENT '备注',
  del_flag TINYINT  COMMENT '删除状态，0-正常,1-已删除',
  create_by char(32)  COMMENT '创建人',
  create_time DATETIME  COMMENT '创建时间',
  update_by char(32)  COMMENT '更新人',
  update_time DATETIME  COMMENT '更新时间',
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



DROP TABLE IF EXISTS buss_bill;
CREATE TABLE buss_bill (
  id char(32) NOT NULL COMMENT '主键ID',
  transportation char(1) NOT NULL COMMENT '运输方式，A-空运air、S-海运sea',
  cust_name char(32) NOT NULL COMMENT '客户姓名',
  national_area char(1) NOT NULL COMMENT '国家地区，1-西马、2-新加坡',
  express_no varchar(256) NOT NULL COMMENT '快递单号',
  cust_addr varchar(256) NOT NULL COMMENT '客户地址',
  contacts char(32) NOT NULL COMMENT '联系人',
  contacts_number char(16) NOT NULL COMMENT '联系电话',
  sensitive_goods char(1) NOT NULL COMMENT '敏感货 0 否 1 是 ',
  weight char(8) COMMENT '重量',
  volume_len_width_height char(16) COMMENT '体积长、宽、高，逗号分开',
  volume_weight char(8) COMMENT '体积重（KG）',
  tracking_number char(16) COMMENT '运单号',
  deliver_company varchar(36) COMMENT '派送公司',
  freight_unit_price char(8) COMMENT '运费单价',
  total_amount char(10) COMMENT '运费',
  cust_pay_date char(19) COMMENT '客户付款时间,yyyy-MM-dd HH:mm:ss',
  cust_pay_channel char(10) COMMENT '客户付款渠道',
  cust_settlement char(2) COMMENT '客户结算渠道，11-微信转账，12-微信红包，21-支付宝、31-马币',
  exchange_rate char(8) COMMENT '马币汇率，两位小数',
  company_settlement char(2) COMMENT '公司结算渠道，11-微信转账，12-微信红包，21-支付宝',
  company_settlement_date char(19) COMMENT '公司结算渠道日期,yyyy-MM-dd HH:mm:ss',
  remark varchar(128)  COMMENT '备注',
  del_flag char(1)  COMMENT '删除状态，0-正常,1-已删除',
  create_by char(32)  COMMENT '创建人',
  create_time char(17)  COMMENT '创建时间',
  update_by char(32)  COMMENT '更新人',
  update_time char(17)  COMMENT '更新时间',
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT = '接单表';

