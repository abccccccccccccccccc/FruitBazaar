package com.fruitbazaar.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 单位表 实体类
 */
@Data
@TableName(value = "unit")
@ApiModel(value="Unit", description="单位表")
public class Unit{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一标识")
    private Long id;

    @ApiModelProperty("单位名称")
    private String unitName;

}

