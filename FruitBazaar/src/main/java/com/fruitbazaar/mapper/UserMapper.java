package com.fruitbazaar.mapper;



import com.fruitbazaar.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
