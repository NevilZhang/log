package com.log.common.utils.fileutils;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    /**
     * 生成文件
     *
     * @param file       文件
     * @param isOverride 是否需要覆盖原有内容
     * @throws IOException IO异常
     */
    public static void makeFile(File file, Boolean isOverride) throws IOException {
        // 判断文件是否存在
        if (file.exists()) {
            // 文件存在, 判断是否需要覆盖原有内容
            if (isOverride) {
                // 无需覆盖
                return;
            }

            // 文件存在, 需要覆盖原有内容, 删除原有文件
            if (!file.delete()) {
                // 无法删除异常
                throw new IOException("删除文件失败");
            }
        } else {
            // 文件不存在, 生成路径上的目录
            if (file.getParentFile().mkdirs()) {
                throw new IOException("创建路径目录失败");
            }

            // 创建新的文件
            if (!file.createNewFile()) {
                throw new IOException("创建文件失败");
            }
        }
    }
}
