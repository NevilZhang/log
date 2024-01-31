package com.log.api;

import com.log.core.log.Logger;

public interface LogExecutePlan {
    /**
     * 获取Logger
     *
     * @return Logger
     */
    Logger getLogger();

    /**
     * 日志写入
     *
     * @param LOG Logger
     */
    void writeLog(Logger LOG);
}
