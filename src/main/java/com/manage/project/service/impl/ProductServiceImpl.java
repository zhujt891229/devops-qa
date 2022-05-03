package com.manage.project.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.project.mapper.ProductMapper;
import com.manage.project.model.Product;
import com.manage.project.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public PageInfo<Product> getProductList(String distributorId, String cellphone, Integer pageNum, Integer pageSize) {
        Page<Product> page = PageHelper.startPage(pageNum, pageSize)
                .doSelectPage(() -> productMapper.selectByParams(distributorId, cellphone));
        return new PageInfo<>(page);
    }
}
