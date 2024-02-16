package com.duanli.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class Type implements Serializable {
    @TableId
    private Integer tid;
    private String tname;
    @Version
    private Integer version;
    private Integer isDeleted;
    @Serial
    private static final long serialVersionUID = 1L;
}
