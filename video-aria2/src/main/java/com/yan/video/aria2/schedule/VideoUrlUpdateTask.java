package com.yan.video.aria2.schedule;

import com.yan.video.aria2.dao.DownloadDao;
import com.yan.video.entity.Video;
import com.yan.video.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 由于aria2下载不一定马上就会创建文件，因此写个定时任务每10分钟检查一次该任务是否已经创建文件
 * 如果创建了文件就把文件路径保存
 *
 * @author likaiyan
 * @date 2020/7/2 5:51 下午
 */
@Component
public class VideoUrlUpdateTask {

    @Autowired
    private DownloadDao downloadDao;

    @Autowired
    private VideoMapper videoMapper;

    @Value("${aria2.preDir}")
    private String ARIA2_PRE_DIR;

    @Value("${server.video.preUrl}")
    private String VIDEO_PRE_URL;

    @Value("${aria2.actual.dir}")
    private String ARIA2_ACTUAL_DIR;

    @Scheduled(cron = "0 0/10 * * * ？")
    public void updateUrl() {
        List<Video> videos = downloadDao.getUncreatedFileVideos();
        if (!CollectionUtils.isEmpty(videos)) {
            videos.forEach(video -> {
                video.setUrl(VIDEO_PRE_URL + ARIA2_PRE_DIR + getVideoName(ARIA2_ACTUAL_DIR + video.getName()));
                videoMapper.updateByPrimaryKey(video);
            });
        }
    }

    private String getVideoName(String path) {
        List<File> files = new ArrayList<>();
        findFilesInPath(new File(path), files);
        if (!CollectionUtils.isEmpty(files)) {
            files.sort(Comparator.comparingLong(File::length));
            return files.get(0).getName();
        }
        return "";
    }

    private void findFilesInPath(File file, List<File> files) {
        if (file.exists() && !file.isDirectory()) {
            files.add(file);
            return;
        }
        File[] fileList = file.listFiles();
        if (fileList != null) {
            Arrays.stream(fileList).forEach(f -> findFilesInPath(f, files));
        }
    }

}
