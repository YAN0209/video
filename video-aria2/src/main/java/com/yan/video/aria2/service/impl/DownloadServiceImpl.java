package com.yan.video.aria2.service.impl;

import com.yan.video.aria2.config.Constants;
import com.yan.video.aria2.dto.*;
import com.yan.video.aria2.service.DownloadService;
import com.yan.video.entity.DownloadTask;
import com.yan.video.entity.DownloadTaskExample;
import com.yan.video.entity.Video;
import com.yan.video.exception.ApiRuntimeException;
import com.yan.video.mapper.DownloadTaskMapper;
import com.yan.video.mapper.VideoMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author likaiyan
 * @date 2020/7/1 3:57 下午
 */
@Service
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private DownloadTaskMapper downloadTaskMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${aria2.url}")
    private String ARIA2_URL;

    @Value("${aria2.token}")
    private String ARIA2_TOKEN;

    @Value("${aria2.preDir}")
    private String ARIA2_PRE_DIR;

    @Value("${server.video.preUrl}")
    private String VIDEO_PRE_URL;

    @Value("${aria2.actual.dir}")
    private String ARIA2_ACTUAL_DIR;

    @Override
    public void addTaskQueue(AddTaskParam addTaskParam) {
        Optional.ofNullable(videoMapper.selectByPrimaryKey(addTaskParam.getVideoId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.videoNoFound"));
        this.rabbitTemplate.convertAndSend("hello", addTaskParam);
    }

    @Override
    @RabbitListener(queues = "task")
    @RabbitHandler
    @Transactional
    public void addTask(AddTaskParam addTaskParam) {
        Video video = videoMapper.selectByPrimaryKey(addTaskParam.getVideoId());
        //下载请求的参数
        AriaDownloadReqOption ariaDownloadReqOption = new AriaDownloadReqOption();
        ariaDownloadReqOption.setOut(video.getName());
        ariaDownloadReqOption.setDir(ARIA2_PRE_DIR + video.getName());
        //下载请求实体
        AriaReq ariaReq = new AriaReq();
        ariaReq.setMethod(Constants.ARIA2_METHOD_ADD_URI);
        List<Object> params = new ArrayList<>();
        params.add(ARIA2_TOKEN);
        params.add(Collections.singletonList(addTaskParam.getUrl()));
        params.add(ariaDownloadReqOption);
        ariaReq.setParams(params);

        AriaResp ariaResp = restTemplate.postForObject(ARIA2_URL, ariaReq, AriaResp.class);
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.setGid(ariaResp.getId());
        downloadTask.setVideoId(video.getId());
        downloadTaskMapper.insert(downloadTask);

//        video.setUrl(VIDEO_PRE_URL + ARIA2_PRE_DIR + getVideoName(ARIA2_ACTUAL_DIR + video.getName()));
//        videoMapper.updateByPrimaryKey(video);

    }

    @Override
    public ProgressResp getTaskProgress(int videoId) {
        DownloadTaskExample example = new DownloadTaskExample();
        DownloadTaskExample.Criteria criteria = example.createCriteria();
        criteria.andVideoIdEqualTo(videoId);
        List<DownloadTask> downloadTasks = downloadTaskMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(downloadTasks)) {
            throw new ApiRuntimeException("exception.VideoNoFound");
        }
        AriaReq ariaReq = new AriaReq();
        ariaReq.setMethod(Constants.ARIA2_METHOD_TELL_STATUS);
        ariaReq.setParams(Collections.singletonList(downloadTasks.get(0).getGid()));
        AriaResp resp = restTemplate.postForObject(ARIA2_URL, ariaReq, AriaResp.class);
        float completedLength = Float.parseFloat(resp.getResult().getOrDefault("completedLength", "0"));
        float totalLength = Float.parseFloat(resp.getResult().getOrDefault("totalLength", "0"));

        ProgressResp progressResp = new ProgressResp();
        progressResp.setVideoId(videoId);
        progressResp.setTaskId(downloadTasks.get(0).getGid());
        if (totalLength < 1) {
            progressResp.setProgress("0");
        } else {
            DecimalFormat df = new DecimalFormat(".00");
            progressResp.setProgress(df.format(completedLength / totalLength) + "%");
        }

        return progressResp;

    }

}
