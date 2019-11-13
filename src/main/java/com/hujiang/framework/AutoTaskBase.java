package com.hujiang.framework;

import com.hujiang.common.utils.ThreadUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public abstract class AutoTaskBase {

    @Value("${autotask:false}")
    protected boolean autotask;

    @Value("${server.port}")
    protected String port;

    public void exec(Runnable runnable) {
        try {

            System.out.println("autotask: 》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》"+autotask);
            System.out.println("port: 》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》"+port);
            if(!autotask) {
                return;
            }
            ThreadUtils.async(runnable);
            int i = 0;
        }
        catch (Exception e) {
            // logger
        }
    }
}
