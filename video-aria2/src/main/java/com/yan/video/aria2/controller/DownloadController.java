package com.yan.video.aria2.controller;

import com.yan.video.aria2.dto.AddTaskParam;
import com.yan.video.aria2.service.DownloadService;
import com.yan.video.common.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author likaiyan
 * @date 2020/7/1 3:58 下午
 */
@RestController
@RequestMapping("/download")
@Api(tags = "downloadController", description = "下载相关接口")
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    @PostMapping("/addTask")
    public ResponseResult addTask(@Valid @RequestBody AddTaskParam addTaskParam, BindingResult bindingResult){
        downloadService.addTask(addTaskParam);
        return ResponseResult.success(null);
    }

    @GetMapping("/{videoId}/progress")
    public ResponseResult getTaskProgress(@PathVariable Integer videoId){
        return ResponseResult.success(downloadService.getTaskProgress(videoId));
    }

}
