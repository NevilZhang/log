package com.log.core.log;

import com.log.core.config.LogConfig;
import com.log.core.log.impl.FileLoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LogCore {
    // 实现单例模式
    private static LogCore instance;

    // 默认日志Logger Factory
    private static LoggerFactory loggerFactory = null;

    // Debug日志Logger Factory
    private static LoggerFactory debugLoggerFactory = null;

    // 用户自定义日志Logger Factory
    private static Map<String, LoggerFactory> customLoggerFactoryMap = null;

    /**
     * 默认日志核心构造器
     */
    public LogCore() {
        // 注册默认日志文件, 默认的loggerFactory设置为FileLoggerFactory
        File loggerFile = new File(LogConfig.DEFAULT_LOG_FILE.getAbsolutePath());
        loggerFactory = new FileLoggerFactory(loggerFile);

        // 注册Debug日志文件, 默认的debugLoggerFactory设置为FileLoggerFactory
        File debugLoggerFile = new File(LogConfig.DEFAULT_DEBUG_LOG_FILE.getAbsolutePath());
        debugLoggerFactory = new FileLoggerFactory(debugLoggerFile);

        // 用户自定义的日志文件
        if (LogConfig.CUSTOM_LOG_FILE_LIST == null) {
            LogConfig.CUSTOM_LOG_FILE_LIST = new ArrayList<>();
        }
        if (customLoggerFactoryMap == null) {
            customLoggerFactoryMap = new HashMap<>();
        }
        LogConfig.CUSTOM_LOG_FILE_LIST.forEach(file -> {
            String key = file.getAbsolutePath();
            File customLogFile = new File(key);
            customLoggerFactoryMap.put(key, new FileLoggerFactory(customLogFile));
        });
    }

    /**
     * 获取Log Core
     *
     * @return LogCore
     */
    public static LogCore getInstance() {
        if (instance == null) instance = new LogCore();
        return instance;
    }

    /**
     * 设置Log Core
     *
     * @param instance LogCore
     */
    public static void setInstance(LogCore instance) {
        LogCore.instance = instance;
    }

    /**
     * 获取默认日志Logger Factory
     *
     * @return LoggerFactory
     */
    public static LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }

    /**
     * 设置默认日志Logger Factory
     *
     * @param loggerFactory 默认日志Logger Factory
     */
    public static void setLoggerFactory(LoggerFactory loggerFactory) {
        LogCore.loggerFactory = loggerFactory;
    }

    /**
     * 获取Debug日志Logger Factory
     *
     * @return LoggerFactory
     */
    public static LoggerFactory getDebugLoggerFactory() {
        return debugLoggerFactory;
    }

    /**
     * 设置Debug日志Logger Factory
     *
     * @param debugLoggerFactory Debug日志Logger Factory
     */
    public static void setDebugLoggerFactory(LoggerFactory debugLoggerFactory) {
        LogCore.debugLoggerFactory = debugLoggerFactory;
    }

    /**
     * 获取用户自定义日志Logger Factory
     *
     * @return LoggerFactory
     */
    public static LoggerFactory getCustomLoggerFactory(String customLoggerFactoryName) {
        return customLoggerFactoryMap.get(customLoggerFactoryName);
    }

    /**
     * 设置用户自定义日志Logger Factory
     *
     * @param customLoggerFactory 用户自定义日志Logger Factory
     */
    public static void setCustomLoggerFactory(String customLoggerFactoryName, LoggerFactory customLoggerFactory) {
        if (customLoggerFactoryMap == null) customLoggerFactoryMap = new HashMap<>();
        LogCore.customLoggerFactoryMap.put(customLoggerFactoryName, customLoggerFactory);
    }

}
