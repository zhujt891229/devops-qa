package com.manage.project.service;

import com.github.pagehelper.PageInfo;
import com.manage.project.model.Product;

public interface ProductService {
    PageInfo<Product> getProductList(String distributorId, String cellphone, Integer pageNum, Integer pageSize);
}
