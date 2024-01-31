package com.log.api;

import com.log.core.log.Logger;

public class LogExecutor {
    public static void start(LogExecutePlan plan) {
        // LOG获取
        Logger LOG = plan.getLogger();
        if (LOG == null) return;

        // 日志写入
        plan.writeLog(LOG);
    }
}
