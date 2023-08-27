package com.manage.project.controller;

import com.manage.project.common.Response;
import com.manage.project.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    @CrossOrigin
    @GetMapping("/digData")
    public Response<String> digData(){
        int i = lotteryService.digData();
        int j = 0 ;

        return Response.ok();
    }
}