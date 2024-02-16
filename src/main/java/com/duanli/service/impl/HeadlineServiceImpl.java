package com.duanli.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duanli.mapper.HeadlineMapper;
import com.duanli.pojo.Headline;
import com.duanli.pojo.vo.PortalVo;
import com.duanli.service.HeadlineService;
import com.duanli.utils.JwtHelper;
import com.duanli.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline> implements HeadlineService {
    @Autowired
    private HeadlineMapper headlineMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result findNewsPage(PortalVo portalVo) {
        IPage<Headline> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());
        IPage<Map<Object, Object>> mapIPage = headlineMapper.selectPageMap(page, portalVo);
        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageData", page.getRecords());
        pageInfo.put("pageNum", page.getCurrent());
        pageInfo.put("pageSize", page.getSize());
        pageInfo.put("totalPage", page.getPages());
        pageInfo.put("totalSize", page.getTotal());
        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("pageInfo", pageInfo);
        return Result.ok(pageInfoMap);
    }

    @Override
    public Result showHeadlineDetail(Integer hid) {
        Map<Object, Object> headlineMap = headlineMapper.selectDetailMap(hid);

        // save headline
        Headline headline = new Headline();
        headline.setHid(hid);
        headline.setPageViews((Integer) headlineMap.get("pageViews") + 1);
        headline.setVersion((Integer) headlineMap.get("version"));
        headlineMapper.updateById(headline);

        Map<String, Object> headlineInfo = new HashMap<>();
        headlineInfo.put("headline", headlineMap);
        return Result.ok(headlineInfo);
    }

    @Override
    public Result publish(String token, Headline headline) {
        Integer userId = jwtHelper.getUserId(token);
        headline.setPublisher(userId);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headline.setPageViews(0);
        headlineMapper.insert(headline);
        return Result.ok(null);
    }

    @Override
    public Result findHeadlineByHid(Integer hid) {
        Headline headline = headlineMapper.selectById(hid);
        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("headline", headline);
        return Result.ok(pageInfoMap);
    }

    @Override
    public Result updateHeadLine(Headline headline) {
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();
        headline.setVersion(version);
        headline.setUpdateTime(new Date());
        headlineMapper.updateById(headline);
        return Result.ok(null);
    }
}
