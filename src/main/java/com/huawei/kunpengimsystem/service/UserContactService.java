package com.huawei.kunpengimsystem.service;

import com.huawei.kunpengimsystem.entity.UserContact;

import java.util.List;

public interface UserContactService {
    List<UserContact> getAllUserContactByUserId(Integer userId);
}
