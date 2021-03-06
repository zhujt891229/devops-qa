package com.icbc.qa.aspect;

import com.icbc.qa.model.base.SysLogInfo;

public class SysLogInfoUtil {
    private static ThreadLocal<SysLogInfo> sysTl=new ThreadLocal<SysLogInfo>();
    public static SysLogInfo getSysLogInfo(){
        SysLogInfo sysLogInfo=sysTl.get();
        if(sysLogInfo==null){
            sysTl.set(new SysLogInfo());
        }
        return sysTl.get();
    }
}
