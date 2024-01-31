package com.log.core.log.impl;

import com.log.core.log.Logger;
import com.log.core.model.enums.LogLevelEnum;

import java.io.PrintWriter;
import java.io.StringWriter;

public class FileLogger extends Logger {

    // FileLoggerFactory
    private final FileLoggerFactory loggerFactory;

    // Logger类别
    private final String loggerName;

    // 当前日志级别, 高于或等于当前级别的日志均会打印
    protected LogLevelEnum currentLogLevel = LogLevelEnum.INFO;

    /**
     * 构造器
     *
     * @param loggerName    loggerName
     * @param loggerFactory loggerFactory
     */
    public FileLogger(FileLoggerFactory loggerFactory, String loggerName) {
        this.loggerName = loggerName;
        this.loggerFactory = loggerFactory;
    }

    /**
     * 获取Logger名称
     *
     * @return String
     */
    public String getLoggerName() {
        return loggerName;
    }

    @Override
    public Boolean isEnabled() {
        return loggerFactory.isEnabled();
    }

    @Override
    public void trace(String message) {
        if (isLevelEnabled(LogLevelEnum.TRACE)) {
            loggerFactory.writeIntoFile(LogLevelEnum.TRACE, this, message);
        }
    }


    @Override
    public void debug(String message) {
        debug(message, null);
    }

    @Override
    public void debug(Throwable throwable) {
        debug(null, throwable);
    }

    @Override
    public void debug(String message, Throwable throwable) {
        if (isLevelEnabled(LogLevelEnum.DEBUG)) {
            String stackTrace = (throwable == null ? "" : getStackTrace(throwable));
            String msg = (message == null ? "" : message + "\n");
            String buffer = msg + stackTrace;
            loggerFactory.writeIntoFile(LogLevelEnum.DEBUG, this, buffer);
        }
    }

    @Override
    public void info(String message) {
        info(message, null);
    }

    @Override
    public void info(Throwable throwable) {
        info(null, throwable);
    }

    @Override
    public void info(String message, Throwable throwable) {
        if (isLevelEnabled(LogLevelEnum.INFO)) {
            String stackTrace = (throwable == null ? "" : getStackTrace(throwable));
            String msg = (message == null ? "" : message + "\n");
            String buffer = msg + stackTrace;
            loggerFactory.writeIntoFile(LogLevelEnum.INFO, this, buffer);
        }
    }

    @Override
    public void warn(String message) {
        warn(message, null);
    }

    @Override
    public void warn(Throwable throwable) {
        warn(null, throwable);
    }

    @Override
    public void warn(String message, Throwable throwable) {
        if (isLevelEnabled(LogLevelEnum.WARN)) {
            String stackTrace = (throwable == null ? "" : getStackTrace(throwable));
            String msg = (message == null ? "" : message + "\n");
            String buffer = msg + stackTrace;
            loggerFactory.writeIntoFile(LogLevelEnum.WARN, this, buffer);
        }
    }

    @Override
    public void error(String message) {
        error(message, null);
    }

    @Override
    public void error(Throwable throwable) {
        error(null, throwable);
    }

    @Override
    public void error(String message, Throwable throwable) {
        if (isLevelEnabled(LogLevelEnum.ERROR)) {
            String stackTrace = (throwable == null ? "" : getStackTrace(throwable));
            String msg = (message == null ? "" : message + "\n");
            String buffer = msg + stackTrace;
            loggerFactory.writeIntoFile(LogLevelEnum.ERROR, this, buffer);
        }
    }

    @Override
    public void setCurrentLevel(LogLevelEnum level) {
        currentLogLevel = level;
    }

    /**
     * 当前级别是否开启打印日志
     *
     * @param level 目标级别
     * @return Boolean
     */
    private Boolean isLevelEnabled(LogLevelEnum level) {
        return (level.getLevel() >= currentLogLevel.getLevel());
    }

    /**
     * 异常堆栈转成文本
     *
     * @param throwable 异常
     * @return String
     */
    private static String getStackTrace(Throwable throwable) {
        if (throwable == null) {
            return "";
        }
        StringWriter sw = new StringWriter(4096);
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }
}
