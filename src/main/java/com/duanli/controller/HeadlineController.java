package com.duanli.controller;

import com.duanli.pojo.Headline;
import com.duanli.service.HeadlineService;
import com.duanli.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("headline")
public class HeadlineController {
    @Autowired
    private HeadlineService headlineService;

    @PostMapping("publish")
    public Result publish(@RequestHeader String token, @RequestBody Headline headline) {
        System.out.println("HeadlineController.publish");
        Result result = headlineService.publish(token, headline);
        return result;
    }

    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(Integer hid) {
        System.out.println("HeadlineController.findHeadlineByHid");
        Result result = headlineService.findHeadlineByHid(hid);
        return result;
    }

    @PostMapping("update")
    public Result update(@RequestBody Headline headline) {
        System.out.println("HeadlineController.update");
        Result result = headlineService.updateHeadLine(headline);
        return result;
    }

    @PostMapping("removeByHid")
    public Result removeById(Integer hid) {
        headlineService.removeById(hid);
        return Result.ok(null);
    }
}
