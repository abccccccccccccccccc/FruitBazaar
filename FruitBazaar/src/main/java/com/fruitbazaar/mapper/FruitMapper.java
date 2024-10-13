package com.fruitbazaar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fruitbazaar.model.entity.Fruit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface FruitMapper  extends BaseMapper<Fruit> {

}
