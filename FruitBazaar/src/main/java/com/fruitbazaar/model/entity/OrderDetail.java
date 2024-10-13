package com.fruitbazaar.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName(value = "order_detail")
@ApiModel(value = "OrderDetail", description = "订单明细信息")
public class OrderDetail {
    @ApiModelProperty(value = "唯一标识")
    private Integer id;

    @ApiModelProperty(value = "订单id，关联订单表的ID")
    private Integer orderId;

    @ApiModelProperty(value = "水果id，关联水果表的ID")
    private Integer fruitId;

    @ApiModelProperty(value = "水果名称，对应水果表的名称")
    private String fruitName;

    @ApiModelProperty(value = "水果单价")
    private Double price;

    @ApiModelProperty(value = "下单数量")
    private Integer num;

    @ApiModelProperty(value = "单位")
    private String unitId;

    @ApiModelProperty(value = "水果产地")
    private String origin;

    @ApiModelProperty(value = "水果描述")
    private String desctext;
}

