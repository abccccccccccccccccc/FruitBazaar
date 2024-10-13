package com.fruitbazaar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fruitbazaar.model.entity.Orders;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OrderMapper   extends BaseMapper<Orders> {

}
