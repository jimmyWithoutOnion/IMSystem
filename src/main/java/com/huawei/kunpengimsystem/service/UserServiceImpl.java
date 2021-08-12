package com.huawei.kunpengimsystem.service;

import com.huawei.kunpengimsystem.entity.User;
import com.huawei.kunpengimsystem.mapper.UserMapper;

import javax.annotation.Resource;

public class UserServiceImpl implements UserService {
    @Resource(name = "UserMapper")
    private UserMapper userMapper;

    @Override
    public User login(String name, String password) {
        return userMapper.selectUserByNameAndPassword(name, password);
    }
}
