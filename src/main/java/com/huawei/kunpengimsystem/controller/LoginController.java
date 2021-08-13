package com.huawei.kunpengimsystem.controller;

import com.huawei.kunpengimsystem.entity.User;
import com.huawei.kunpengimsystem.service.UserService;
import com.huawei.kunpengimsystem.utils.Result;
import com.huawei.kunpengimsystem.utils.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource(name = "UserService")
    private UserService userService;

    @RequestMapping("/checkLogin")
    public Result checkLogin(String username, String password) {
        User user = userService.login(username, password);
        if (user == null) {
            return ResultUtil.fail("用户名或密码不正确");
        } else {
            return ResultUtil.success(null);
        }
    }

}
