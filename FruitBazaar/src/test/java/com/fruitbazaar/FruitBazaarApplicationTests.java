package com.fruitbazaar;

import com.fruitbazaar.controller.EmployeeController;
import com.fruitbazaar.controller.LoginController;
import com.fruitbazaar.mapper.EmployeeMapper;
import com.fruitbazaar.model.dto.EmployeeLoginDTO;
import com.fruitbazaar.model.dto.EmployeePageQueryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FruitBazaarApplicationTests {
    @Autowired
    private LoginController loginController;
    @Autowired
    private EmployeeController employeeController;

    @Test
    void contextLoads() {
    }

    @Test
    public void testLogin() throws Exception {
        EmployeeLoginDTO employeeLoginDTO=new EmployeeLoginDTO();
        employeeLoginDTO.setUsername("admin");
        employeeLoginDTO.setPassword("123456");
        System.out.println(loginController.login(employeeLoginDTO));
    }

    @Test
    public void testEmployeePage() throws Exception{
        EmployeePageQueryDTO employeePageQueryDTO=new EmployeePageQueryDTO();
        employeePageQueryDTO.setPage(1);
        employeePageQueryDTO.setLimit(10);
        System.out.println(employeeController.page(employeePageQueryDTO));
    }
}
