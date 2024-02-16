package com.duanli.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duanli.mapper.UserMapper;
import com.duanli.pojo.User;
import com.duanli.service.UserService;
import com.duanli.utils.JwtHelper;
import com.duanli.utils.MD5Util;
import com.duanli.utils.Result;
import com.duanli.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = userMapper.selectOne(lambdaQueryWrapper);
        // username wrong -> 501
        if (loginUser == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        // password correct -> 200 + token
        if (!StringUtils.isEmpty(user.getUserPwd()) && MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())) {
            String token = jwtHelper.createToken(user.getUid());
            Map<Object, Object> data = new HashMap<>();
            data.put("token", token);
            return Result.ok(data);
        }
        // password wrong -> 503
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result getUserInfo(String token) {
        // check token expiration
        if (jwtHelper.isExpiration(token)) {
            System.out.println("token expired");
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        // decrypt -> uid
        Integer userId = jwtHelper.getUserId(token);
        // get User
        User user = userMapper.selectById(userId);
        // fail
        if (user == null) {
            System.out.println("get user failed");
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        //success
        user.setUserPwd("");
        Map<Object, Object> data = new HashMap<>();
        data.put("loginUser", user);
        return Result.ok(data);
    }

    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        if (user != null) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        return Result.ok(null);
    }

    @Override
    public Result register(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User existUser = userMapper.selectOne(lambdaQueryWrapper);
        if (existUser != null) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        userMapper.insert(user);
        return Result.ok(null);
    }

}
