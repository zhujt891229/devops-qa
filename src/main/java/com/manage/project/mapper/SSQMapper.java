package com.manage.project.mapper;

import com.manage.project.model.SSQDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SSQMapper {
    @Insert(value = " <script> " +
            " insert into shuangseqiu (code,date,week,red," +
            " blue,sales,pool,content,first_count,first_money," +
            " second_count,second_money,third_count,third_money," +
            " fourth_count,fourth_money,fifth_count,fifth_money," +
            " sixth_count,sixth_money,create_time,update_time) " +
            " values (#{ssq.code}," +
            " #{ssq.date},#{ssq.week},#{ssq.red}," +
            " #{ssq.blue}," +
            " #{ssq.sales},#{ssq.poolmoney},#{ssq.content}," +
            " #{ssq.firstCount},#{ssq.firstMoney}," +
            " #{ssq.secondCount},#{ssq.secondMoney}," +
            " #{ssq.thirdCount},#{ssq.thirdMoney}," +
            " #{ssq.fourthCount},#{ssq.fourthMoney}," +
            " #{ssq.fifthCount},#{ssq.fifthMoney}," +
            " #{ssq.sixthCount},#{ssq.sixthMoney}," +
            " now(),now())" +
            " on duplicate key update update_time = now()" +
            " </script> ")
    int save(@Param("ssq") SSQDetail ssq);

    int batchSave(@Param("list") List<SSQDetail> list);
}
