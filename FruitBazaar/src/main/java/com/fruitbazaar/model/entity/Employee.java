package com.fruitbazaar.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * 员工表
 */
@Data
@TableName(value = "employee")
@ApiModel(value="Employee", description="员工表")
public class Employee {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long positionId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "电话号码")
    private String phoneNumber;

    @ApiModelProperty(value = "性别，0女1男")
    private Byte gender;

    @ApiModelProperty(value = "头像")
    private String img;

    @ApiModelProperty(value = "身份证号")
    private String careNumber;

    @ApiModelProperty(value = "家庭住址")
    private String homeAddress;

    @ApiModelProperty(value = "入职日期")
    private LocalDateTime entryDate;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "状态：1正常，0异常")
    private Integer status;
}
