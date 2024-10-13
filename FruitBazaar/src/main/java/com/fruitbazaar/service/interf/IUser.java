package com.fruitbazaar.service.interf;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fruitbazaar.model.dto.EmployeeLoginDTO;
import com.fruitbazaar.model.dto.UserLoginDTO;
import com.fruitbazaar.model.entity.Employee;
import com.fruitbazaar.model.entity.User;

public interface IUser extends IService<User> {
    User getUser(UserLoginDTO userLoginDTO);
}
