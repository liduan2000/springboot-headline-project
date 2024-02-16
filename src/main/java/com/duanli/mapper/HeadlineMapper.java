package com.duanli.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.duanli.pojo.Headline;
import com.duanli.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface HeadlineMapper extends BaseMapper<Headline> {
    IPage<Map<Object, Object>> selectPageMap(IPage<Headline> page, @Param("portalVo") PortalVo portalVo);

    Map<Object, Object> selectDetailMap(Integer hid);
}
