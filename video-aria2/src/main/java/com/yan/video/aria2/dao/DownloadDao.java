package com.yan.video.aria2.dao;

import com.yan.video.entity.Video;

import java.util.List;

/**
 * @author likaiyan
 * @date 2020/7/2 5:54 下午
 */
public interface DownloadDao {

    /**
     * 获取添加了下载任务但未创建文件的视频
     * @return 视频列表
     */
    List<Video> getUncreatedFileVideos();

}
