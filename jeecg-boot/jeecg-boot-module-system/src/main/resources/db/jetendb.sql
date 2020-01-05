-- 创建mysql库
-- create database `jetendb` default character set utf8mb4 collate utf8mb4_general_ci;

DROP TABLE IF EXISTS t_bill;
CREATE TABLE t_bill (
  id BIGINT(20) NOT NULL COMMENT '主键ID',
  cust_name char(32) NOT NULL COMMENT '客户姓名',
  national_area int NOT NULL COMMENT '1-西马、2-新加坡',
  express_no varchar(256) NOT NULL COMMENT '快递单号',
  cust_addr varchar(256) NOT NULL COMMENT '客户地址',
  contacts char(32) NOT NULL COMMENT '联系人',
  contacts_number char(16) NOT NULL COMMENT '联系电话',
  sensitive_goods int NOT NULL COMMENT '敏感货 0 否 1 是 ',
  weight int COMMENT '重量',
  volume_len_width_height char(16) COMMENT '体积长、宽、高，逗号分开',
  volume_weight int COMMENT '体积重',
  tracking_number char(16) COMMENT '运单号',
  deliver_company varchar(36) COMMENT '派送公司',
  freight_unit_price int COMMENT '运费单价',
  total_amount int COMMENT '运费',
  cust_pay_date char(10) COMMENT '客户付款时间,yyyy-mm-dd',
  cust_pay_channel char(10) COMMENT '客户付款渠道',
  cust_settlement int COMMENT '客户结算渠道，11-微信转账，12-微信红包，21-支付宝、31-马币',
  exchange_rate int COMMENT '马币汇率',
  company_settlement int COMMENT '公司结算渠道，11-微信转账，12-微信红包，21-支付宝',
  company_settlement_date char(10) COMMENT '公司结算渠道日期',
  remark varchar(128)  COMMENT '备注',
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
