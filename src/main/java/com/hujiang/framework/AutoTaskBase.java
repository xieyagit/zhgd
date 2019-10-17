package com.hujiang.framework;

import com.hujiang.common.utils.ThreadUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public abstract class AutoTaskBase {

    @Value("${autotask:false}")
    protected boolean autotask;

    public void exec(Runnable runnable) {
        try {
            if(!autotask) {
                return;
            }
            runnable.run();
        }
        catch (Exception e) {
            // logger
        }
    }
}
