package com.log.core.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.getProperty;

public class LogConfig {

    // 日志基线目录
    private static final File BASE_PATH = new File(getProperty("user.home"), ".log");

    // 默认Log日志文件
    public static final File DEFAULT_LOG_FILE = new File(BASE_PATH, "default.log");

    // 默认Debug Log日志文件
    public static final File DEFAULT_DEBUG_LOG_FILE = new File(BASE_PATH, "defaultDebug.log");

    // 自定义日志文件列表
    public static List<File> CUSTOM_LOG_FILE_LIST = new ArrayList<>();

    // 日志开关
    public static Boolean IS_ENABLED = true;

    public static final String TRACE = "[HotCode3]-[TRACE] %s";

    public static final String DEBUG = "[HotCode3]-[DEBUG] %s";

    public static final String INFO = "[HotCode3]-[INFO] %s";

    public static final String WARN = "[HotCode3]-[WARN] %s";

    public static final String ERROR = "[HotCode3]-[ERROR] %s";
}
