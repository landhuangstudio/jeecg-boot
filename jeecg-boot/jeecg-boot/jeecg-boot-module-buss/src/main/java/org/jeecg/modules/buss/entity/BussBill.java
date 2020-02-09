package org.jeecg.modules.buss.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @Author scott
 * @since 2018-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BussBill implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    /**
     * 运输方式，A-空运air、S-海运sea
     */
    @Dict(dicCode = "transportation")
    private String transportation;
    /**
     * 客户姓名
     */
    private String custName;
    /**
     * 国家地区（1：西马 2：新加坡）
     */
    @Dict(dicCode = "national_area")
    private String nationalArea;
    /**
     * 快递单号
     */
    private String expressNo;
    /**
     * 客户地址
     */
    private String custAddr;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 联系电话
     */
    private String contactsNumber;
    /**
     * 敏感货（0：否 1：是）
     */
    @Dict(dicCode = "sensitive_goods")
    private String sensitiveGoods;
    /**
     * 重量
     */
    private String weight;
    /**
     * 体积长、宽、高，逗号分开
     */
    private String volumeLenWidthHeight;
    /**
     * 体积重（KG）
     */
    private String volumeWeight;
    /**
     * 运单号
     */
    private String trackingNumber;
    /**
     * 派送公司
     */
    private String deliverCompany;
    /**
     * 运费单价
     */
    private String freightUnitPrice;
    /**
     * 运费
     */
    private String totalAmount;
    /**
     * 客户付款时间,yyyy-MM-dd HH:mm:ss
     */
    private String custPayDate;
    /**
     * 客户付款渠道
     */
    private String custPayChannel;
    /**
     * 客户结算渠道，11-微信转账，12-微信红包，21-支付宝、31-马币
     */
    @Dict(dicCode = "cust_settlement")
    private String custSettlement;
    /**
     * 马币汇率
     */
    private String exchangeRate;
    /**
     * 公司结算渠道，11-微信转账，12-微信红包，21-支付宝
     */
    @Dict(dicCode = "company_settlement")
    private String companySettlement;
    /**
     * 公司结算渠道日期
     */
    private String companySettlementDate;
    /**
     * 备注
     */
    private String remark;

    /**
     * 删除状态
     */
    @TableLogic
    @Dict(dicCode = "del_flag")
    private String delFlag;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private String updateTime;


}
