package com.log.core.log;

import com.log.core.model.enums.LogLevelEnum;

public abstract class Logger {
    /**
     * 日志总开关
     *
     * @return Boolean
     */
    public abstract Boolean isEnabled();

    /**
     * Print Trace Log
     *
     * @param message 日志内容
     */
    public abstract void trace(String message);

    /**
     * Print Debug Log
     *
     * @param message 日志内容
     */
    public abstract void debug(String message);

    /**
     * Print Debug Log
     *
     * @param throwable 错误异常
     */
    public abstract void debug(Throwable throwable);

    /**
     * Print Debug Log
     *
     * @param message   日志内容
     * @param throwable 错误异常
     */
    public abstract void debug(String message, Throwable throwable);

    /**
     * Print Info Log
     *
     * @param message 日志内容
     */
    public abstract void info(String message);

    /**
     * Print Info Log
     *
     * @param throwable 错误异常
     */
    public abstract void info(Throwable throwable);

    /**
     * Print Info Log
     *
     * @param message   日志内容
     * @param throwable 错误异常
     */
    public abstract void info(String message, Throwable throwable);

    /**
     * Print Warn Log
     *
     * @param message 日志内容
     */
    public abstract void warn(String message);

    /**
     * Print Warn Log
     *
     * @param throwable 错误异常
     */
    public abstract void warn(Throwable throwable);

    /**
     * Print Warn Log
     *
     * @param message   日志内容
     * @param throwable 错误异常
     */
    public abstract void warn(String message, Throwable throwable);

    /**
     * Print Error Log
     *
     * @param message 日志内容
     */
    public abstract void error(String message);

    /**
     * Print Error Log
     *
     * @param throwable 错误异常
     */
    public abstract void error(Throwable throwable);

    /**
     * Print Error Log
     *
     * @param message   日志内容
     * @param throwable 错误异常
     */
    public abstract void error(String message, Throwable throwable);

    /**
     * 设置当前日志级别
     *
     * @param level 日志级别
     */
    public abstract void setCurrentLevel(LogLevelEnum level);
}
