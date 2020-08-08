package com.yan.video.aria2.service;

import com.yan.video.aria2.dto.ProgressResp;
import com.yan.video.aria2.dto.AddTaskParam;

/**
 * @author likaiyan
 * @date 2020/7/1 12:01 下午
 */
public interface DownloadService {

    void addTaskQueue(AddTaskParam addTaskParam);

    void addTask(AddTaskParam addTaskParam);

    ProgressResp getTaskProgress(int videoId);

}