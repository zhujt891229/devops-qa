package com.manage.project.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.project.exception.ParamException;
import com.manage.project.mapper.ProductMapper;
import com.manage.project.model.Product;
import com.manage.project.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${uploadFolder}")
    private String uploadFolder;

    @Resource
    private ProductMapper productMapper;

    @Override
    public PageInfo<Product> getProductList(String distributorId, String cellphone, Integer pageNum, Integer pageSize) {
        Page<Product> page = PageHelper.startPage(pageNum, pageSize)
                .doSelectPage(() -> productMapper.selectByParams(distributorId, cellphone));
        return new PageInfo<>(page);
    }

    @Override
    public int saveProduct(Product product) {
        Long id = product.getId();
        int affected;
        if(null==id){
            affected = productMapper.insert(product);
        }else{
            affected = productMapper.update(product);
        }
        return affected;
    }

    @Override
    public String saveFile(MultipartFile file) {

        //用于保存图片上传路径
        if(file.isEmpty()){
            throw new ParamException("图片为空");
        }
        long size = file.getSize();//8859  8.6K
        if(size>10*1024*1024){
            throw new ParamException("图片大于10M!");
        }
        //获取上传图片的文件名
        String fileFullName = file.getOriginalFilename();//1.jpg

        //获取图片扩展名
        String type = fileFullName.substring(fileFullName.lastIndexOf(".") + 1);
//        String type1 = file.getContentType();//image/jpg

        //获取图片上传的时间，以时间作为图片的名字可以防止图片重名
        String nowTimeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        //生成随机整数防止文件名重复
        int randomNum = (int) ((Math.random() * 9 + 1) * 1000);
        List<String> typeList = new ArrayList<>();
        typeList.add("bmp");
        typeList.add("jpg");
        typeList.add("png");
        typeList.add("jpeg");
        typeList.add("gif");
        //判断是否为要求的格式
        assert type != null;
        if (typeList.contains(StringUtils.lowerCase(type))) {

            //将图片上传到指定路径的文件夹
            File dest = new File(uploadFolder+nowTimeStr+"_"+randomNum+ "." + type);

            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest); // 保存文件
                return dest.getAbsolutePath();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }else{
            throw new ParamException("图片格式不正确!");
        }

    }

}
