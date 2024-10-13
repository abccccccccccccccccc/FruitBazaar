package com.fruitbazaar.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 水果种类表 实体类
 */
@Data
@TableName(value = "fruit_type")
@ApiModel(value = "FruitType", description = "水果种类表")
public class FruitType {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一标识")
    private Long id;

    @ApiModelProperty("种类编号，A开始编号")
    private String code;

    @ApiModelProperty("种类名称")
    private String typeName;

}

