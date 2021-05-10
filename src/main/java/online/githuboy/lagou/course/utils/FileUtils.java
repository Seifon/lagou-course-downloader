package online.githuboy.lagou.course.utils;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static void save(byte[] bytes, File path) {
        FileUtil.writeBytes(bytes, path);
    }

    public static String getCorrectFileName(String originFileName) {
        return originFileName.replaceAll("[\\\\s/:*?\"<>|]",
                "");
    }

    /**
     * 不存在就新建文件
     */
    public static void createNewFile(String filepath) {
        File file = new File(filepath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String str = "\\\\/////////////A:*B??:::\"<C\">||||";
        String r = str.replaceAll("[\\\\s/:*?\"<>|]", "");
        System.out.println(FileUtils.getCorrectFileName("01 | Spring Data JPA 初识"));
        System.out.println(r);
    }
}
