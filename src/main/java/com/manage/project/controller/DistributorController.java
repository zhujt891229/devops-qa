package com.manage.project.controller;

import com.github.pagehelper.PageInfo;
import com.manage.project.common.Response;
import com.manage.project.model.Distributor;
import com.manage.project.service.DistributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/distributor")
public class DistributorController {
    @Autowired
    private DistributorService distributorService;
@PostAuthorize("hasAnyAuthority('admins')")
    @GetMapping("/getDistributorList")
    public Response<PageInfo<Distributor>> getDistributorList(String cellphone, String address, Integer pageNum, Integer pageSize){
        if(null==pageNum||pageNum<1){
            pageNum=1;
        }
        if(null==pageSize||pageSize<1){
            pageSize=5;
        }
        PageInfo<Distributor> distributorPage = distributorService.getDistributorList(cellphone,address, pageNum, pageSize);
        return Response.ok(distributorPage);
    }

    @PostMapping("/saveDistributor")
    public Response<Integer> saveDistributor(@RequestBody Distributor distributor){
        int i = distributorService.saveDistributor(distributor);
        return Response.ok(i);
    }
}
