package com.duanli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.duanli.pojo.Type;
import com.duanli.utils.Result;

public interface TypeService extends IService<Type> {
    Result findAllTypes();
}
