package com.duanli;

import com.duanli.utils.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtTest {
    @Autowired
    private JwtHelper jwtHelper;

    @Test
    public void test() throws InterruptedException {
        //生成 传入用户标识
        String token = jwtHelper.createToken(1);
        System.out.println("token = " + token);
        //解析用户标识
        int userId = jwtHelper.getUserId(token);
        System.out.println("userId = " + userId);

        //校验是否到期! false 未到期 true到期
        boolean expiration = jwtHelper.isExpiration(token);
        System.out.println("expiration = " + expiration);
    }

}
