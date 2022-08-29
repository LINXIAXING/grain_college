package com.gzuniversity.eduorder.controller;


import com.gzuniversity.commonutils.R;
import com.gzuniversity.eduorder.service.TPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/orderservice/log")
@CrossOrigin
public class TPayLogController {
    @Autowired
    private TPayLogService tPayLogService;

    @GetMapping("/createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo) {
        Map map = tPayLogService.createNative(orderNo);
        return R.ok().data(map);
    }

}

