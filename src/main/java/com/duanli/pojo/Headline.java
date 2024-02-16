package com.duanli.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class Headline implements Serializable {
    @TableId
    private Integer hid;
    private String title;
    private String article;
    private Integer type;
    private Integer publisher;
    private Integer pageViews;
    private Date createTime;
    private Date updateTime;
    @Version
    private Integer version;
    private Integer isDeleted;
    @Serial
    private static final long serialVersionUID = 1L;
}
