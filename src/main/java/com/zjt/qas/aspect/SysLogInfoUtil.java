package com.zjt.qas.aspect;

import com.zjt.qas.model.base.SysLogInfo;

public class SysLogInfoUtil {
    private static ThreadLocal<SysLogInfo> sysTl= new ThreadLocal<>();
    public static SysLogInfo getSysLogInfo(){
        SysLogInfo sysLogInfo=sysTl.get();
        if(sysLogInfo==null){
            sysTl.set(new SysLogInfo());
        }
        return sysTl.get();
    }
}
