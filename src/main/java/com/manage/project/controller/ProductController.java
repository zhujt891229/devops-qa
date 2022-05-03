package com.manage.project.controller;

import com.github.pagehelper.PageInfo;
import com.manage.project.common.ResultInfo;
import com.manage.project.model.Product;
import com.manage.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProductList")
    public ResultInfo getProductList(String distributorId, String cellphone, Integer pageNum, Integer pageSize){
        if(null==pageNum||pageNum<1){
            pageNum=1;
        }
        if(null==pageSize||pageSize<1){
            pageSize=5;
        }
        PageInfo<Product> productPage = productService.getProductList(distributorId, cellphone, pageNum, pageSize);
        return ResultInfo.ok(productPage);
    }

    @PostMapping("/savePhoto")
    public ResultInfo savePhoto(@RequestParam("file") MultipartFile file){
        String uploadPath = productService.saveFile(file);
        return ResultInfo.ok().setData(uploadPath);
    }

    @PostMapping("/saveProduct")
    public ResultInfo saveProduct(@RequestBody Product product){
        int i = productService.saveProduct(product);
        return ResultInfo.ok();
    }


}
