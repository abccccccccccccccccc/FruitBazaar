package com.fruitbazaar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fruitbazaar.model.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper  extends BaseMapper<Employee> {
}
