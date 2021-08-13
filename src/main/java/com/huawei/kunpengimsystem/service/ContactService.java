package com.huawei.kunpengimsystem.service;

import com.huawei.kunpengimsystem.entity.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getAllContactByUserId (Integer userId);
}
