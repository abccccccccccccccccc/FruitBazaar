package com.fruitbazaar.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderPageQueryDTO implements Serializable {
    //订单编号
    private String code;
//    private String name;

    //页码
    private int page;

    //每页显示记录数
    private int limit;
}
