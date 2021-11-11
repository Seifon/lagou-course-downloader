package online.githuboy.lagou.course;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import online.githuboy.lagou.course.utils.ConfigUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import sun.jvm.hotspot.gc_implementation.g1.HeapRegion;

import java.io.File;
import java.util.*;

public class FileTest {

    /**
     * 将文章，单独拷贝到一个目录
     */
    @Test
    public void copyDoc() {
        List<File> mp4_dir = FileUtil.loopFiles(ConfigUtil.readValue("mp4_dir"));

        mp4_dir.stream().filter(file -> file.getName().contains(".md"))
                .forEach(
                        file -> {
                            String path = file.getPath();
                            path = StringUtils.replace(path, "/文档", "");
                            path = StringUtils.replace(path, "zzzz", "zzzzzz");
                            System.out.println(path);
                            FileUtil.copyFile(file, new File(path));
                        }
                );
    }
}
