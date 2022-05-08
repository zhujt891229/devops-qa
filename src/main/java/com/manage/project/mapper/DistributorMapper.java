package com.manage.project.mapper;

import com.manage.project.model.Distributor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DistributorMapper {
    @Select(value=" <script> " +
            " select t.id ," +
            " t.name, " +
            " t.cellphone, " +
            " t.telephone, " +
            " t.address, " +
            " t.create_time, " +
            " t.update_time " +
            " from distributor t " +
            " where 1=1 " +
            " <if test='cellphone != null and cellphone != \"\"'>" +
            " and t.cellphone like concat('%', #{cellphone,jdbcType=VARCHAR},'%')" +
            " </if>" +
            " <if test='address != null and address != \"\"'>" +
            " and t.address like concat('%', #{address,jdbcType=VARCHAR},'%')" +
            " </if>" +
            " </script> ")
    List<Distributor> selectByParams(String cellphone,String address);

    @Insert(value = " <script> " +
            " insert into distributor (name,cellphone,telephone,address,create_time," +
            " update_time) values (#{distributor.name}," +
            " #{distributor.cellphone},#{distributor.telephone},#{distributor.address}," +
            " now(),now())" +
            " </script> ")
    int insert(@Param("distributor") Distributor distributor);

    @Update(value = " <script> " +
            " update distributor set name=#{distributor.name},cellphone=#{distributor.cellphone}, " +
            " telephone=#{distributor.telephone},address=#{distributor.address}, " +
            " update_time=now() where id=#{distributor.id} " +
            " </script> ")
    int update(@Param("distributor") Distributor distributor);
}
