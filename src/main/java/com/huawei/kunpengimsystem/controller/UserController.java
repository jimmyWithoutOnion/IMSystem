package com.huawei.kunpengimsystem.controller;

import com.huawei.kunpengimsystem.entity.User;
import com.huawei.kunpengimsystem.service.ContactService;
import com.huawei.kunpengimsystem.service.UserContactService;
import com.huawei.kunpengimsystem.service.UserService;
import com.huawei.kunpengimsystem.utils.Result;
import com.huawei.kunpengimsystem.utils.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource(name = "UserService")
    private UserService userService;

    @Resource(name = "ContactService")
    private ContactService contactService;

    @Resource(name = "UserContactService")
    private UserContactService userContactService;

    @RequestMapping("/queryUserInformationByUserId")
    public Result queryUserInformationByUserId(Integer userId) {
        User user = userService.getInformationById(userId);
        return ResultUtil.success(user);
    }

//    @RequestMapping("/queryAllContactByUserId")
//    public Result queryAllContactByUserId(Integer userId) {
//
//    }
}
