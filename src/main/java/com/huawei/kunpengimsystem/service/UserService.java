package com.huawei.kunpengimsystem.service;

import com.huawei.kunpengimsystem.entity.User;

public interface UserService {
    User login(String name, String password);

    User getInformationById(Integer userId);
}
