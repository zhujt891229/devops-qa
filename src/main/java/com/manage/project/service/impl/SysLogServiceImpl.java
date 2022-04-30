package com.manage.project.service.impl;

import com.manage.project.mapper.SysLogMapper;
import com.manage.project.model.base.SysLogInfo;
import com.manage.project.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public int addSysLog(SysLogInfo sysLogInfo) {
        int result;
        try{
            result = sysLogMapper.addSysLog(sysLogInfo);
        }catch(Exception e){
            e.printStackTrace();
            result=0;
        }
        return result;
    }
}
