package com.fruitbazaar.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(value = "orders")
@ApiModel(value = "Orders", description = "订单信息")
public class Orders {
    @ApiModelProperty(value = "唯一标识")
    private Integer id;

    @ApiModelProperty(value = "订单号，格式为日期(yyyyMMdd)+0001")
    private String code;

    @ApiModelProperty(value = "客户id，关联用户表的用户ID")
    private Integer userId;

    @ApiModelProperty(value = "订单总价")
    private Double oldPrice;

    @ApiModelProperty(value = "折扣后价格")
    private Double newPrice;

    @ApiModelProperty(value = "订单状态：1已完成，2已取消，3订单异常", example = "1")
    private Integer status;

    @ApiModelProperty(value = "支付方式：1会员卡余额支付，2微信支付，3支付宝支付，4现金支付", example = "1")
    private Integer payType;

    @ApiModelProperty(value = "支付时间", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "收银员id")
    private Integer employeeId;

    @ApiModelProperty(value = "创建时间", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "订单明细")
    @TableField(exist = false) // 非数据库字段
    private List<OrderDetail> orderDetails;
}

