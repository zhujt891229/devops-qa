package com.zjt.qas.service;

import com.github.pagehelper.PageInfo;
import com.zjt.qas.model.entity.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    PageInfo<Product> getProductList(String distributorId, String cellphone, Integer pageNum, Integer pageSize);

    int saveProduct(Product product);

    String saveFile(MultipartFile file);
}
