package com.icbc.qa.service.impl;

import com.icbc.qa.mapper.SysLogMapper;
import com.icbc.qa.model.base.SysLogInfo;
import com.icbc.qa.service.SysLogService;
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
