package com.fruitbazaar.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
@TableName(value = "user")
@ApiModel(value="User", description="会员表")
public class User {
    @ApiModelProperty(value = "唯一标识")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户类型：1普通用户，2会员")
    private Byte userType;

    @ApiModelProperty(value = "账号状态：1正常，0注销账号")
    private Byte status;

    @ApiModelProperty(value = "电话号码")
    private String phoneNumber;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "性别:0女生，1男生")
    private Byte gender;

    @ApiModelProperty(value = "账户余额")
    private Double balance;

    @ApiModelProperty(value = "累计消费")
    private Double consumption;

    @ApiModelProperty(value = "会员积分")
    private Integer points;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "创建人id")
    private Integer creatorId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人id")
    private Integer modifierId;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyTime;
}

