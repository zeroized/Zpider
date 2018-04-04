package com.zeroized.spider.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Zero on 2018/3/7.
 */
public class FileUtil {
    public static List<String> readLines(String filePath, String encoding) throws IOException {
        return FileUtils.readLines(new File(filePath), encoding);
    }

    public static String read(String filePath) throws IOException {
        return read(new File(filePath), "utf-8");
    }

    public static String read(File file, String encoding) throws IOException {
        return FileUtils.readFileToString(file, encoding);
    }

    public static void write(String filePath, String content) {
        write(filePath, content, "utf-8");
    }

    public static void write(String filePath, String content, String encoding) {

    }
}
