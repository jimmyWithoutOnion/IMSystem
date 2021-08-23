package com.huawei.kunpengimsystem.controller;

import com.huawei.kunpengimsystem.entity.User;
import com.huawei.kunpengimsystem.service.UserService;
import com.huawei.kunpengimsystem.utils.NativeUtil;
import com.huawei.kunpengimsystem.utils.Result;
import com.huawei.kunpengimsystem.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource(name = "UserService")
    private UserService userService;

    @RequestMapping("/checkLogin")
    public Result checkLogin(String username, String password) {
        NativeUtil nativeUtil = new NativeUtil();
        // 调用jni时间接口
        nativeUtil.getTimeMs();
        String encryptedPassword = password;
        if (password != null) {
            // 调用jniSha256的接口
            encryptedPassword = nativeUtil.getSha256Digest(password);
        }
        User user = userService.login(username, encryptedPassword);
        if (user == null) {
            return ResultUtil.fail("用户名或密码不正确");
        } else {
            return ResultUtil.success(user);
        }
    }
}
