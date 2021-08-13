package com.huawei.kunpengimsystem.service;

import com.huawei.kunpengimsystem.entity.User;
import com.huawei.kunpengimsystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String name, String password) {
        return userMapper.selectUserByNameAndPassword(name, password);
    }

    @Override
    public User getInformationById(Integer userId) {
        return userMapper.selectUserByUserId(userId);
    }
}
