package com.manage.project.mapper;

import com.manage.project.model.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface ProductMapper {

    @Select(value=" <script> " +
            " select t.id ," +
            " t.customer_name as customerName, " +
            " t.customer_cellphone as customerCellphone, " +
            " t.customer_address as customerAddress, " +
            " t.distributor_id as distributorId, " +
            " t.sale_time as saleTime, " +
            " t.create_time as createTime, " +
            " t.update_time as updateTime, " +
            " t.bar_code_photo as barCodePhoto " +
            " from product t " +
            " where 1=1 " +
            " <if test='distributorId != null and distributorId != \"\"'>" +
            " and t.distributor_id = #{distributorId,jdbcType=VARCHAR}" +
            " </if>" +
            " <if test='cellphone != null and cellphone != \"\"'>" +
            " and t.customer_cellphone like concat('%', #{cellphone,jdbcType=VARCHAR},'%')" +
            " </if>" +
            " </script> ")
    List<Product> selectByParams(String distributorId, String cellphone);

    @Insert(value = " <script> " +
            " insert into product (bar_code_photo,customer_name,customer_cellphone,customer_address," +
            " distributor_id,sale_time,create_time,update_time) values (#{product.barCodePhoto}," +
            " #{product.customerName},#{product.customerCellphone},#{product.customerAddress}," +
            " #{product.distributorId},now(),now(),now())" +
            " </script> ")
    int insert(@Param("product")Product product);

    @Update(value = " <script> " +
            " update product set bar_code_photo=#{product.barCodePhoto},customer_name=#{product.customerName}, " +
            " customer_cellphone=#{product.customerCellphone},customer_address=#{product.customerAddress}, " +
            " update_time=now() where id=#{product.id} " +
            " </script> ")
    int update(@Param("product")Product product);
}
