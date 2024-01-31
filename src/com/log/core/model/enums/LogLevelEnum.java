package com.log.core.model.enums;

public enum LogLevelEnum {
    TRACE(0), DEBUG(1), INFO(2), WARN(3), ERROR(4), OFF(99);

    private final Integer level;

    LogLevelEnum(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    /**
     * 解析日志级别
     *
     * @param level 级别
     * @return LogLevelEnum
     */
    public static LogLevelEnum parseLevel(Integer level) {
        for (LogLevelEnum logLevelEnum : LogLevelEnum.values()) {
            if (logLevelEnum.level == level) {
                return logLevelEnum;
            }
        }
        return OFF;
    }
}
