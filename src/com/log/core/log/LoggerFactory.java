package com.log.core.log;

public abstract class LoggerFactory {
    /**
     * 获取默认的Logger Factory
     *
     * @return LoggerFactory
     */
    public static LoggerFactory getLoggerFactory() {
        return LogCore.getInstance().getLoggerFactory();
    }

    /**
     * 获取Debug的Logger Factory
     *
     * @return LoggerFactory
     */
    public static LoggerFactory geDebugLoggerFactory() {
        return LogCore.getInstance().getDebugLoggerFactory();
    }

    /**
     * 获取用户自定义的Logger Factory
     *
     * @return LoggerFactory
     */
    public static LoggerFactory geCustomLoggerFactory(String customLoggerFactoryName) {
        return LogCore.getInstance().getCustomLoggerFactory(customLoggerFactoryName);
    }

    /**
     * 获取Logger
     *
     * @param loggerName loggerName
     * @return Logger
     */
    public abstract Logger getLogger(String loggerName);

    /**
     * 获取Debug Logger
     *
     * @param loggerName loggerName
     * @return Logger
     */
    public abstract Logger getDebugLogger(String loggerName);

    /**
     * 获取Custom Logger
     *
     * @param loggerName loggerName
     * @return Logger
     */
    public abstract Logger getCustomLogger(String customLoggerFactoryName, String loggerName);
}
