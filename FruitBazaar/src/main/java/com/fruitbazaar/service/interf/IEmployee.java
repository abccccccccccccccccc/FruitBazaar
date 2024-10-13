package com.fruitbazaar.service.interf;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fruitbazaar.common.result.PageResult;
import com.fruitbazaar.common.result.Result;
import com.fruitbazaar.model.dto.EmployeeLoginDTO;
import com.fruitbazaar.model.dto.EmployeePageQueryDTO;
import com.fruitbazaar.model.entity.Employee;

public interface IEmployee extends IService<Employee> {
    Employee queryEmployee(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 根据id修改员工账号状态
     * @param id
     */
    boolean updateStatus(int id);

    /**
     * 编辑员工信息
     * @param employee
     */
//    boolean update(Employee employee);

}
