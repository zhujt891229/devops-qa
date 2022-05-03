package com.manage.project.mapper;

import com.manage.project.model.Product;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface ProductMapper {

    @Select(value=" <script> " +
            " select t.id ," +
            " t.customer_name as customerName, " +
            " t.customer_cellphone as customerCellphone, " +
            " customer_address as customerAddress, " +
            " distributor_id as distributorId, " +
            " sale_time as saleTime, " +
            " create_time as createTime, " +
            " update_time as updateTime, " +
            " bar_code_photo as barCodePhoto " +
            " from product t " +
            " where 1=1 " +
            " <if test='distributorId != null and distributorId != \"\"'>" +
            " and distributor_id = #{distributorId,jdbcType=VARCHAR}" +
            " </if>" +
            " <if test='cellphone != null and cellphone != \"\"'>" +
            " and customer_cellphone like concat('%', #{cellphone,jdbcType=VARCHAR},'%')" +
            " </if>" +
            " </script> ")
    List<Product> selectByParams(String distributorId, String cellphone);

}
