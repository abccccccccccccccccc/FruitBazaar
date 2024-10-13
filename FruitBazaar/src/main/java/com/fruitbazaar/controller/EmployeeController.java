package com.fruitbazaar.controller;


import com.fruitbazaar.common.constant.MessageConstant;
import com.fruitbazaar.common.result.PageResult;
import com.fruitbazaar.common.result.Result;
import com.fruitbazaar.mapper.EmployeeMapper;
import com.fruitbazaar.model.dto.EmployeeLoginDTO;
import com.fruitbazaar.model.dto.EmployeePageQueryDTO;
import com.fruitbazaar.model.entity.Employee;
import com.fruitbazaar.service.interf.IEmployee;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
    @Autowired
    private IEmployee employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("/page")
    @ApiOperation("员工分页查询")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        log.info("员工分页查询，参数为：{}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping("/delete")
    @ApiOperation("员工-删除")
    public Result<String> delete(int id){
        log.info("员工删除，参数为：{}", id);
        //做假删除，只修改员工账号状态
        boolean res=employeeService.updateStatus(id);
        return res?Result.success():Result.error(MessageConstant.OPTION_ERROR);
    }

    @ApiOperation("新增员工,返回新增的员工id")
    @PostMapping("/add")
    public Result add(@RequestBody Employee employee){
        log.info("\n新增员工：{}",employee);
        employeeService.save(employee);
        EmployeeLoginDTO employeeLoginDTO=new EmployeeLoginDTO();
        employeeLoginDTO.setUsername(employee.getUsername());
        employeeLoginDTO.setPassword(employee.getPassword());
        int id=employeeService.queryEmployee(employeeLoginDTO).getId().intValue();
        return id!=0?Result.success(id):Result.error(MessageConstant.OPTION_ERROR);
    }


    @ApiOperation("根据id查询员工")
    @PostMapping("/getById")
    public Employee getById(int id) {
        Employee employee = employeeService.getById(id);
        return employee;
    }


    @PostMapping("/update")
    @ApiOperation("编辑员工信息")
    public Result update(@RequestBody Employee employee){
        log.info("编辑员工信息：{}", employee);
        boolean res=employeeService.updateById(employee);
        return res?Result.success():Result.error(MessageConstant.OPTION_ERROR);
    }

}
