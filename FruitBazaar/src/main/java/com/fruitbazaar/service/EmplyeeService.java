package com.fruitbazaar.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fruitbazaar.common.result.PageResult;
import com.fruitbazaar.mapper.EmployeeMapper;
import com.fruitbazaar.common.constant.MessageConstant;
import com.fruitbazaar.common.constant.StatusConstant;
import com.fruitbazaar.common.exception.AccountNotFoundException;
import com.fruitbazaar.common.exception.BaseException;
import com.fruitbazaar.common.exception.PasswordErrorException;
import com.fruitbazaar.model.dto.EmployeeLoginDTO;
import com.fruitbazaar.model.dto.EmployeePageQueryDTO;
import com.fruitbazaar.model.entity.Employee;
import com.fruitbazaar.service.interf.IEmployee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmplyeeService extends ServiceImpl<EmployeeMapper, Employee> implements IEmployee {
    @Autowired
    private EmployeeMapper mapper;
    @Override
    public Employee queryEmployee(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();
        LambdaQueryWrapper<Employee> wrapper= Wrappers.<Employee>lambdaQuery();
        wrapper.eq(Employee::getUsername,username);
        Employee employee=mapper.selectOne(wrapper);
//        处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        //密码比对
        // TODO 后期需要进行md5加密，然后再进行比对
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号已注销
            throw new BaseException(MessageConstant.ACCOUNT_LOGGED_OUT);
        }
        return employee;
    }

    /**
     * 分页查询
     *
     * @param employeePageQueryDTO
     * @return
     */
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        //分页参数
        Page<Employee> page = new Page(employeePageQueryDTO.getPage(), employeePageQueryDTO.getLimit());
        //wrapper组装查询where条件
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getStatus,1);
        if (employeePageQueryDTO.getName()!=null&&!employeePageQueryDTO.getName().isEmpty()){
            wrapper.eq(Employee::getName,employeePageQueryDTO.getName());
        }
        page = this.mapper.selectPage(page, wrapper);
        long total = this.mapper.selectCount(wrapper);
        List<Employee> records = page.getRecords();
        return new PageResult(total, records);
    }

    /**
     * 根据id修改员工账号状态
     * @param id
     */
    @Override
    public boolean updateStatus(int id) {
        LambdaUpdateWrapper<Employee> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Employee::getId,id)
                .set(Employee::getStatus,StatusConstant.DISABLE);
        int res=mapper.update(null,wrapper);
        return res>0;
    }

}
