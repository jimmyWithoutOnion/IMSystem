package com.huawei.kunpengimsystem.service.Impl;

import com.huawei.kunpengimsystem.entity.UserContact;
import com.huawei.kunpengimsystem.mapper.UserContactMapper;
import com.huawei.kunpengimsystem.service.UserContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("UserContactService")
@Transactional
public class UserContactServiceImpl implements UserContactService {
    @Autowired
    private UserContactMapper userContactMapper;

    @Override
    public List<UserContact> getAllUserContactByUserId(Integer userId) {
        return userContactMapper.selectUserContactByUserId(userId);
    }
}
