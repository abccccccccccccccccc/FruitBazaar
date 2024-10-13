package com.fruitbazaar.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 * 水果 实体类
 */
@Data
@TableName(value = "fruit")
@ApiModel(value = "Fruit", description = "水果信息")
public class Fruit {

    @ApiModelProperty("唯一标识")
    private Long id;

    @ApiModelProperty("水果编号，如类型编号+序列号（例如：A0001）")
    private String code;

    @ApiModelProperty("水果名")
    private String name;

    @ApiModelProperty("种类id，关联水果种类表的ID")
    private Long fruitTypeId;

    @ApiModelProperty("原价")
    private Double oldPrice;

    @ApiModelProperty("折扣后价格")
    private Double newPrice;

    @ApiModelProperty("产地")
    private String origin;

    @ApiModelProperty("描述")
    private String desctext;

    @ApiModelProperty("级别：A，B，C或无级别")
    private String level;

    @ApiModelProperty("水果图")
    private String img;

    @ApiModelProperty("状态：1在售，0售罄，2已删除")
    private Integer status;

    @ApiModelProperty("单位id，关联单位表的ID")
    private Long unitId;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建人id，关联用户表的用户ID")
    private Long creatorId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createdAt;

    @ApiModelProperty("修改人id，关联用户表的用户ID")
    private Long modifierId;
}

