package com.log.core.log.impl;

import com.log.common.utils.fileutils.FileUtils;
import com.log.core.config.LogConfig;
import com.log.core.log.Logger;
import com.log.core.log.LoggerFactory;
import com.log.core.model.enums.LogLevelEnum;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileLoggerFactory extends LoggerFactory implements Closeable {
    // 日志文件
    private final File logFile;

    // 日志文件输出流
    private OutputStreamWriter fileWriter;

    // 默认FileLoggerMap
    private final Map<String, FileLogger> fileLoggerMap = new ConcurrentHashMap<>();

    // Debug FileLoggerMap
    private final Map<String, FileLogger> debugFileLoggerMap = new ConcurrentHashMap<>();

    // 用户自定义 FileLoggerMap
    private final Map<String, Map<String, FileLogger>> customFileLoggerMap = new ConcurrentHashMap<>();

    // 日志头
    private final String logHeader;

    // 是否覆盖
    private final Boolean isOverride;

    // 时间格式模板
    private static final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss.SSS] ", Locale.ENGLISH);

    public FileLoggerFactory(File logFile) {
        this(logFile, true, "Log File " + DATA_FORMAT.format(new Date()));
    }

    public FileLoggerFactory(File logFile, Boolean isOverride, String logHeader) {
        // 日志头
        this.logHeader = (logHeader == null ? "" : logHeader);

        // 是否覆盖
        this.isOverride = isOverride;

        // 日志文件设置
        if (logFile == null) {
            this.logFile = null;
            this.fileWriter = null;
        } else {
            //
            File currentLogFile = null;

            try {
                // 生成日志文件
                currentLogFile = generateLogFile(logFile, isOverride);
                if (currentLogFile != null) {
                    // 输出流绑定日志文件, 使用Append模式, 向后添加内容, 旧数据不覆盖
                    FileOutputStream fos = new FileOutputStream(currentLogFile, true);

                    // 写入绑定输出流
                    fileWriter = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                currentLogFile = null;
            } finally {
                this.logFile = currentLogFile;
            }
        }

        // 写入日志头
        writeIntoFileWithoutFormatting(logHeader + "\n\n");
    }

    /**
     * 关闭输出流
     *
     * @throws IOException IO异常
     */
    @Override
    public synchronized void close() throws IOException {
        if (fileWriter != null) {
            fileWriter.close();
        }
    }

    /**
     * 获取默认Logger
     *
     * @param loggerName loggerName
     * @return Logger
     */
    @Override
    public Logger getLogger(String loggerName) {
        // 定义Key
        String key = loggerName == null ? "default" : loggerName;

        // 查询对应的FileLogger
        FileLogger logger = fileLoggerMap.get(key);

        // logger为空, 重新创建一个新的FileLogger
        if (logger == null) {
            logger = new FileLogger(this, key);
            fileLoggerMap.put(key, logger);
        }
        return logger;
    }

    /**
     * 获取Debug Logger
     *
     * @param loggerName loggerName
     * @return Logger
     */
    @Override
    public Logger getDebugLogger(String loggerName) {
        // 定义Key
        String key = loggerName == null ? "default" : loggerName;

        // 查询对应的FileLogger
        FileLogger logger = debugFileLoggerMap.get(key);

        // logger为空, 重新创建一个新的FileLogger
        if (logger == null) {
            logger = new FileLogger(this, key);
            debugFileLoggerMap.put(key, logger);
        }
        return logger;
    }

    /**
     * 获取自定义的Logger
     *
     * @param customLoggerFactoryName 自定义的LoggerFactory
     * @param loggerName              loggerName
     * @return Logger
     */
    @Override
    public Logger getCustomLogger(String customLoggerFactoryName, String loggerName) {
        // 是否包含自定义的customLoggerFactory
        Map<String, FileLogger> fileLoggerMap;
        if (!customFileLoggerMap.containsKey(customLoggerFactoryName)) {
            fileLoggerMap = new ConcurrentHashMap<>();
            customFileLoggerMap.put(customLoggerFactoryName, fileLoggerMap);
        } else {
            fileLoggerMap = customFileLoggerMap.get(customLoggerFactoryName);
        }

        // 定义Key
        String key = loggerName == null ? "default" : loggerName;

        // 查询对应的FileLogger
        FileLogger logger = fileLoggerMap.get(key);

        // logger为空, 重新创建一个新的FileLogger
        if (logger == null) {
            logger = new FileLogger(this, key);
            fileLoggerMap.put(key, logger);
        }
        return logger;
    }

    /**
     * 生成日志文件（方法级锁）
     *
     * @param logFile    日志文件
     * @param isOverride 是否覆盖
     * @return File
     */
    private static synchronized File generateLogFile(File logFile, Boolean isOverride) {
        if (logFile == null) {
            return null;
        }

        // 日志文件生成结果
        File result = null;
        try {
            // 生成文件
            FileUtils.makeFile(logFile, isOverride);
            result = logFile;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 写入日志文件内容(无Formatting)
     *
     * @param text 文本内容
     */
    synchronized void writeIntoFileWithoutFormatting(String text) {
        if (fileWriter != null) {
            try {
                // Append模式写入
                fileWriter.append(text == null ? "" : text);

                // 清空缓冲区
                fileWriter.flush();
            } catch (Throwable throwable) {
                // 打印异常堆栈
                throwable.printStackTrace();
            }
        }
    }

    /**
     * 写入日志文件内容(Formatting)
     *
     * @param text 文本内容
     */
    synchronized void writeIntoFile(LogLevelEnum logLevel, FileLogger logger, String text) {
        // 文件写入
        if (fileWriter != null) {
            try {
                // 标准时间
                String dateTime = DATA_FORMAT.format(new Date());

                // 日志级别
                String level = null;
                switch (logLevel) {
                    case TRACE:
                        level = "[TRACE]";
                        break;
                    case DEBUG:
                        level = "[DEBUG]";
                        break;
                    case INFO:
                        level = "[INFO]";
                        break;
                    case WARN:
                        level = "[WARN]";
                        break;
                    case ERROR:
                        level = "[ERROR]";
                        break;
                }

                // 获取Logger名称
                String loggerName = logger.getLoggerName();

                // Append模式写入
                fileWriter.append(dateTime).append(" ").append(level).append(" [").append(loggerName).append("] ").append(text).append("\n");

                // 清空缓冲区
                fileWriter.flush();
            } catch (Throwable throwable) {
                // 打印异常堆栈
                throwable.printStackTrace();
            }
        }
    }

    /**
     * 输出日志三要素
     *
     * @return String
     */
    public String toString() {
        return "File = " + logFile + ", isOverride = " + isOverride + ", logHeader = " + logHeader;
    }

    public Boolean isEnabled() {
        return LogConfig.IS_ENABLED;
    }
}
