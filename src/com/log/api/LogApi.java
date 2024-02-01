package com.log.api;

import com.log.core.config.LogConfig;
import com.log.core.log.Logger;

public class LogApi {
    /**
     * trace
     *
     * @param object  正在打印日志的对象
     * @param message 需要打印的日志内容
     */
    public static void trace(Object object, String message) {
        trace(object, message, null);
    }

    /**
     * trace
     *
     * @param object  正在打印日志的对象
     * @param message 需要打印的日志内容
     */
    public static void trace(Object object, String message, String customLoggerFactoryName) {
        LogExecutor.start(new LogExecutePlan() {
            @Override
            public Logger getLogger() {
                // LOG 获取
                Logger LOG;
                if (customLoggerFactoryName != null && !customLoggerFactoryName.isEmpty()) {
                    LOG = Logger.getCustomLogger(customLoggerFactoryName, object.getClass().getName());
                } else {
                    LOG = Logger.getDebugLogger(object.getClass().getName());
                }
                return LOG;
            }

            @Override
            public void writeLog(Logger LOG) {
                // 日志写入
                if (LOG.isEnabled()) {
                    LOG.trace(String.format(LogConfig.TRACE, message));
                }
            }
        });
    }

    /**
     * debug
     *
     * @param object  正在打印日志的对象
     * @param message 需要打印的日志内容
     */
    public static void debug(Object object, String message) {
        debug(object, message, null);
    }

    /**
     * debug
     *
     * @param object    正在打印日志的对象
     * @param throwable 异常
     */
    public static void debug(Object object, Throwable throwable) {
        debug(object, null, throwable);
    }

    /**
     * debug
     *
     * @param object    正在打印日志的对象
     * @param message   需要打印的日志内容
     * @param throwable 异常
     */
    public static void debug(Object object, String message, Throwable throwable) {
        debug(object, message, throwable);
    }

    /**
     * debug
     *
     * @param object    正在打印日志的对象
     * @param message   需要打印的日志内容
     * @param throwable 异常
     */
    public static void debug(Object object, String message, Throwable throwable, String customLoggerFactoryName) {
        LogExecutor.start(new LogExecutePlan() {
            @Override
            public Logger getLogger() {
                // LOG 获取
                Logger LOG;
                if (customLoggerFactoryName != null && !customLoggerFactoryName.isEmpty()) {
                    LOG = Logger.getCustomLogger(customLoggerFactoryName, object.getClass().getName());
                } else {
                    LOG = Logger.getDebugLogger(object.getClass().getName());
                }
                return LOG;
            }

            @Override
            public void writeLog(Logger LOG) {
                // 日志写入
                if (!LOG.isEnabled()) {
                    return;
                }
                String msg = message == null || message.isEmpty() ? "Debug" : message;
                if (throwable == null) {
                    LOG.debug(String.format(LogConfig.DEBUG, msg));
                } else {
                    LOG.debug(String.format(LogConfig.DEBUG, msg), throwable);
                }
            }
        });
    }

    /**
     * info
     *
     * @param object  正在打印日志的对象
     * @param message 需要打印的日志内容
     */
    public static void info(Object object, String message) {
        info(object, message, null);
    }

    /**
     * info
     *
     * @param object    正在打印日志的对象
     * @param throwable 异常
     */
    public static void info(Object object, Throwable throwable) {
        info(object, null, throwable);
    }

    /**
     * info
     *
     * @param object    正在打印日志的对象
     * @param message   需要打印的日志内容
     * @param throwable 异常
     */
    public static void info(Object object, String message, Throwable throwable) {
        info(object, message, throwable);
    }

    /**
     * info
     *
     * @param object    正在打印日志的对象
     * @param message   需要打印的日志内容
     * @param throwable 异常
     */
    public static void info(Object object, String message, Throwable throwable, String customLoggerFactoryName) {
        LogExecutor.start(new LogExecutePlan() {
            @Override
            public Logger getLogger() {
                // LOG 获取
                Logger LOG;
                if (customLoggerFactoryName != null && !customLoggerFactoryName.isEmpty()) {
                    LOG = Logger.getCustomLogger(customLoggerFactoryName, object.getClass().getName());
                } else {
                    LOG = Logger.getDebugLogger(object.getClass().getName());
                }
                return LOG;
            }

            @Override
            public void writeLog(Logger LOG) {
                // 日志写入
                if (!LOG.isEnabled()) {
                    return;
                }
                String msg = message == null || message.isEmpty() ? "Info" : message;
                if (throwable == null) {
                    LOG.info(String.format(LogConfig.INFO, msg));
                } else {
                    LOG.info(String.format(LogConfig.INFO, msg), throwable);
                }
            }
        });
    }


    /**
     * warn
     *
     * @param object  正在打印日志的对象
     * @param message 需要打印的日志内容
     */
    public static void warn(Object object, String message) {
        warn(object, message, null);
    }

    /**
     * warn
     *
     * @param object    正在打印日志的对象
     * @param throwable 异常
     */
    public static void warn(Object object, Throwable throwable) {
        warn(object, null, throwable);
    }

    /**
     * warn
     *
     * @param object    正在打印日志的对象
     * @param message   需要打印的日志内容
     * @param throwable 异常
     */
    public static void warn(Object object, String message, Throwable throwable) {
        warn(object, message, throwable);
    }

    /**
     * warn
     *
     * @param object    正在打印日志的对象
     * @param message   需要打印的日志内容
     * @param throwable 异常
     */
    public static void warn(Object object, String message, Throwable throwable, String customLoggerFactoryName) {
        LogExecutor.start(new LogExecutePlan() {
            @Override
            public Logger getLogger() {
                // LOG 获取
                Logger LOG;
                if (customLoggerFactoryName != null && !customLoggerFactoryName.isEmpty()) {
                    LOG = Logger.getCustomLogger(customLoggerFactoryName, object.getClass().getName());
                } else {
                    LOG = Logger.getDebugLogger(object.getClass().getName());
                }
                return LOG;
            }

            @Override
            public void writeLog(Logger LOG) {
                // 日志写入
                if (!LOG.isEnabled()) {
                    return;
                }
                String msg = message == null || message.isEmpty() ? "Warn" : message;
                if (throwable == null) {
                    LOG.warn(String.format(LogConfig.WARN, msg));
                } else {
                    LOG.warn(String.format(LogConfig.WARN, msg), throwable);
                }
            }
        });
    }

    /**
     * error
     *
     * @param object  正在打印日志的对象
     * @param message 需要打印的日志内容
     */
    public static void error(Object object, String message) {
        error(object, message, null);
    }

    /**
     * error
     *
     * @param object    正在打印日志的对象
     * @param throwable 异常
     */
    public static void error(Object object, Throwable throwable) {
        error(object, null, throwable);
    }

    /**
     * error
     *
     * @param object    正在打印日志的对象
     * @param message   需要打印的日志内容
     * @param throwable 异常
     */
    public static void error(Object object, String message, Throwable throwable) {
        error(object, message, throwable);
    }

    /**
     * error
     *
     * @param object    正在打印日志的对象
     * @param message   需要打印的日志内容
     * @param throwable 异常
     */
    public static void error(Object object, String message, Throwable throwable, String customLoggerFactoryName) {
        LogExecutor.start(new LogExecutePlan() {
            @Override
            public Logger getLogger() {
                // LOG 获取
                Logger LOG;
                if (customLoggerFactoryName != null && !customLoggerFactoryName.isEmpty()) {
                    LOG = Logger.getCustomLogger(customLoggerFactoryName, object.getClass().getName());
                } else {
                    LOG = Logger.getDebugLogger(object.getClass().getName());
                }
                return LOG;
            }

            @Override
            public void writeLog(Logger LOG) {
                // 日志写入
                if (!LOG.isEnabled()) {
                    return;
                }
                String msg = message == null || message.isEmpty() ? "Error" : message;
                if (throwable == null) {
                    LOG.error(String.format(LogConfig.ERROR, msg));
                } else {
                    LOG.error(String.format(LogConfig.ERROR, msg), throwable);
                }
            }
        });
    }
}
