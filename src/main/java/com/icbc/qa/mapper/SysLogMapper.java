package com.icbc.qa.mapper;

import com.icbc.qa.model.base.SysLogInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SysLogMapper {
    @Insert(value = "<script>" +
            "insert into sys_log (level,req_url,method,param,response,total_time,error_content,operation," +
            "client_ip,operator_id,create_time)values (#{sysLog.level},#{sysLog.reqUrl},#{sysLog.method}," +
            "#{sysLog.param},#{sysLog.response},#{sysLog.totalTime},#{sysLog.errorContent}," +
            "#{sysLog.operation},#{sysLog.clientIp},#{sysLog.operatorId},#{now()})" +
            "</script>")
    int addSysLog(@Param("sysLog") SysLogInfo sysLogInfo);

    @Select(value="<script>" +
            "select t.id , t.req_url reqUrl,t.method from adlm_report_error t " +
            "where req_url in ('ADLMReportToDB','JIRAERROR') and operation = 'new'" +
            "</script>")
    List<SysLogInfo> selectExceptSysLogs();

    //todo
    @Update(value="<script>" +
            "update adlm_report_error set operation = 'ok' where id in ()" +
            "</script>")
    int updateSysLog(@Param("ids") String ids);
}
