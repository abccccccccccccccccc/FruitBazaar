package com.fruitbazaar.controller;


import com.fruitbazaar.common.constant.JwtClaimsConstant;
import com.fruitbazaar.common.properties.JwtProperties;
import com.fruitbazaar.mapper.EmployeeMapper;
import com.fruitbazaar.common.result.Result;
import com.fruitbazaar.model.dto.EmployeeLoginDTO;
import com.fruitbazaar.model.dto.UserLoginDTO;
import com.fruitbazaar.model.entity.Employee;
import com.fruitbazaar.model.entity.User;
import com.fruitbazaar.model.vo.EmployeeLoginVO;
import com.fruitbazaar.model.vo.UserLoginVO;
import com.fruitbazaar.service.interf.IEmployee;
import com.fruitbazaar.service.interf.IUser;
import com.fruitbazaar.utils.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private IEmployee emplyeeService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private IUser userService;


    @ApiOperation("员工登录")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        System.out.println(employeeLoginDTO);
        Employee employee = emplyeeService.queryEmployee(employeeLoginDTO);
        //在Serivce做过非空验证了，这里就可以注释掉了
//        if(employee!=null){}

        //登录成功后生成JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .img(employee.getImg())
//                .token("admin-token")
                .token(token)
                .build();
        return Result.success(employeeLoginVO);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login2")
    public Result<UserLoginVO> login2(@RequestBody UserLoginDTO userLoginDTO) {
        System.out.println(userLoginDTO);
        User user = userService.getUser(userLoginDTO);

        //登录成功后生成JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .token(token)
                .build();
        return Result.success(userLoginVO);
    }
}
