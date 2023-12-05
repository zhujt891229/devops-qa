package com.zjt.qas.controller;

import com.zjt.qas.common.Response;
import com.zjt.qas.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 彩票
 */
@RestController
@RequestMapping("/lottery")
public class LotteryController {

    @Autowired
    LotteryService lotteryService;

    /**
     * 挖取双色球开奖数据并存入mysql数据库
     * @return string
     */
    @GetMapping("/digData")
    public Response<String> digData(){
        int i = lotteryService.digData();
        return Response.ok(String.valueOf(i));
    }

    /**
     * 数据太多
     * @return
     */
    //@GetMapping("/generatePool")
    public Response<String> generatePool(){
        lotteryService.generatePool();
        return Response.ok();
    }
}
