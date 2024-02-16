package com.duanli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.duanli.pojo.User;
import com.duanli.utils.Result;

public interface UserService extends IService<User> {
    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result register(User user);
}
