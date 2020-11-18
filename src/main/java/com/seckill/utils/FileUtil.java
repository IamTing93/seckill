package com.seckill.utils;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static void outputToFile(String path, String str, boolean append) {
        File file = new File(path);
        try {
            FileUtils.writeStringToFile(file, str, "UTF-8", append);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
