package com.duanli.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duanli.mapper.TypeMapper;
import com.duanli.pojo.Type;
import com.duanli.service.TypeService;
import com.duanli.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public Result findAllTypes() {
        List<Type> typeList = typeMapper.selectList(null);
        return Result.ok(typeList);
    }
}
