package com.manage.project.service;

import com.github.pagehelper.PageInfo;
import com.manage.project.model.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    PageInfo<Product> getProductList(String distributorId, String cellphone, Integer pageNum, Integer pageSize);

    int saveProduct(Product product);

    String saveFile(MultipartFile file);
}
