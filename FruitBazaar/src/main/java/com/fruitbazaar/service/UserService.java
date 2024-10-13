package com.fruitbazaar.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fruitbazaar.common.constant.MessageConstant;
import com.fruitbazaar.common.constant.StatusConstant;
import com.fruitbazaar.common.exception.AccountNotFoundException;
import com.fruitbazaar.common.exception.BaseException;
import com.fruitbazaar.common.exception.PasswordErrorException;
import com.fruitbazaar.mapper.UserMapper;
import com.fruitbazaar.model.dto.UserLoginDTO;
import com.fruitbazaar.model.entity.Employee;
import com.fruitbazaar.model.entity.User;
import com.fruitbazaar.service.interf.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUser {
    @Autowired
    UserMapper mapper;
    @Override
    public User getUser(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
        LambdaQueryWrapper<User> wrapper= Wrappers.<User>lambdaQuery();
        wrapper.eq(User::getUsername,username);
        User user=mapper.selectOne(wrapper);
//        处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        //密码比对
        // TODO 后期需要进行md5加密，然后再进行比对
        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        if (user.getStatus() == StatusConstant.DISABLE) {
            //账号已注销
            throw new BaseException(MessageConstant.ACCOUNT_LOGGED_OUT);
        }
        return user;
    }
}
