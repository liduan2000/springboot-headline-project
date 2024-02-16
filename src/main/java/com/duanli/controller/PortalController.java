package com.duanli.controller;

import com.duanli.pojo.vo.PortalVo;
import com.duanli.service.HeadlineService;
import com.duanli.service.TypeService;
import com.duanli.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("portal")
public class PortalController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    @GetMapping("findAllTypes")
    public Result findAllTypes() {
        System.out.println("TypeController.findAllTypes");
        Result result = typeService.findAllTypes();
        return result;
    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo) {
        System.out.println("TypeController.findNewsPage");
        Result result = headlineService.findNewsPage(portalVo);
        return result;
    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(@RequestParam Integer hid) {
        System.out.println("PortalController.showHeadlineDetail");
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }
}
