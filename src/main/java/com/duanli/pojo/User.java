package com.duanli.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class User implements Serializable {
    @TableId
    private Integer uid;
    private String username;
    private String userPwd;
    private String nickName;
    @Version
    private Integer version;
    private Integer isDeleted;
    @Serial
    private static final long serialVersionUID = 1L;
}
