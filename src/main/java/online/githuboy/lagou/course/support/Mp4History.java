package online.githuboy.lagou.course.support;

import cn.hutool.core.io.FileUtil;
import online.githuboy.lagou.course.utils.ConfigUtil;
import online.githuboy.lagou.course.utils.FileUtils;
import online.githuboy.lagou.course.utils.ReadTxt;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * mp4视频下载历史信息记录到文件中
 *
 * @author eric
 */
public class Mp4History {

    private static volatile Set<String> historySet = new HashSet<>();
    private static volatile Set<String> skipFileSet = new HashSet<>();

    /**
     * 记录已经下载过的视频id，不要重复下载了。
     */
    static String filePath = "mp4.txt";
    /**
     * 跳过文件的路径
     */
    static String skipFilePath = "skip/mp4.txt";

    static {
        loadSkipFile();
        loadHistory();
    }

    /**
     * 下载完成之后追加到历史文件
     *
     * @param lessonId
     */
    public static void append(String lessonId) {
        if (historySet.contains(lessonId)) {
            return;
        }
        historySet.add(lessonId);
        new ReadTxt().writeFile(filePath, lessonId);
    }

    public static Set<String> loadHistory() {
        Set<String> set = new ReadTxt().readFile(filePath);
        historySet.addAll(set);
        return historySet;
    }

    public static Set<String> loadSkipFile() {
        Set<String> set = new ReadTxt().readFile(ClassLoader.getSystemResource(skipFilePath).getPath());
        skipFileSet.addAll(set);
        return skipFileSet;
    }

    public static boolean contains(String lessonId, String lessonName, String courseId, String courseName) {
        String savePath = ConfigUtil.readValue("mp4_dir");
        lessonName = FileUtils.getCorrectFileName(lessonName);

        String path = String.join(File.separator,
                savePath,
                courseId + "_" + courseName,
                "[" + lessonId + "] " + lessonName + ".mp4");

        boolean exist = skipFileSet.contains(lessonId) || FileUtil.exist(path);
        if (exist) {
            append(lessonId);
        }
        return exist;
    }

}
