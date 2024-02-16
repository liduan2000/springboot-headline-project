package com.duanli.controller;

import com.alibaba.druid.util.StringUtils;
import com.duanli.pojo.User;
import com.duanli.service.UserService;
import com.duanli.utils.JwtHelper;
import com.duanli.utils.Result;
import com.duanli.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("login")
    public Result login(@RequestBody User user) {
        System.out.println("UserController.login");
        Result result = userService.login(user);
        return result;
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token) {
        System.out.println("UserController.getUserInfo");
        Result result = userService.getUserInfo(token);
        return result;
    }

    @PostMapping("checkUserName")
    public Result checkUserName(@RequestParam String username) {
        System.out.println("UserController.checkUserName");
        Result result = userService.checkUserName(username);
        return result;
    }

    @PostMapping("register")
    public Result register(@RequestBody User user) {
        System.out.println("UserController.register");
        Result result = userService.register(user);
        return result;
    }

    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token) {
        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)) {
            //没有传或者过期 未登录
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        return Result.ok(null);
    }
}
