package com.huawei.kunpengimsystem.service;

import com.huawei.kunpengimsystem.entity.Contact;
import com.huawei.kunpengimsystem.mapper.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ContactService")
@Transactional
public class ContactServiceImpl implements ContactService{
    @Autowired
    private ContactMapper contactMapper;

    @Override
    public List<Contact> getAllContactByUserId(Integer userId) {
        return contactMapper.selectContactByUserId(userId);
    }
}
