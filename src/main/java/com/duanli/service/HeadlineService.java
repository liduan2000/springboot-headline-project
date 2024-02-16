package com.duanli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.duanli.pojo.Headline;
import com.duanli.pojo.vo.PortalVo;
import com.duanli.utils.Result;

public interface HeadlineService extends IService<Headline> {
    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(String token, Headline headline);

    Result findHeadlineByHid(Integer hid);

    Result updateHeadLine(Headline headline);
}
