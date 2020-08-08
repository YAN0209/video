package com.yan.video.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.yan.video.admin.dao.VideoServiceMapper;
import com.yan.video.admin.dto.*;
import com.yan.video.entity.*;
import com.yan.video.exception.ApiRuntimeException;
import com.yan.video.mapper.*;
import com.yan.video.admin.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author likaiyan
 * @date 2020/6/8 6:19 下午
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoGroupMapper videoGroupMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private VideoTypeMapper videoTypeMapper;

    @Autowired
    private VideoTagMapper videoTagMapper;

    @Autowired
    private VideoServiceMapper videoServiceMapper;

    @Autowired
    private VideoGroupTypeMapper videoGroupTypeMapper;

    @Autowired
    private VideoGroupTagMapper videoGroupTagMapper;

    @Autowired
    private MessageSource messageSource;

    @Override
    public int addVideoGroup(AddVideoGroupParam addVideoGroupParam) {
        VideoGroupExample example = new VideoGroupExample();
        VideoGroupExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(addVideoGroupParam.getName());
        Optional.ofNullable(videoGroupMapper.selectByExample(example))
                .orElseThrow(() -> new ApiRuntimeException("exception.sameNameExisted"));
        VideoGroup videoGroup = new VideoGroup();
        BeanUtils.copyProperties(addVideoGroupParam, videoGroup);
        videoGroupMapper.insert(videoGroup);
        return videoGroup.getId();
    }

    @Override
    @Transactional
    public void deleteVideoGroup(Integer groupId) {
        VideoGroup videoGroup = Optional.ofNullable(videoGroupMapper.selectByPrimaryKey(groupId))
                .orElseThrow(() -> new ApiRuntimeException("exception.videoNoFound"));
        videoGroup.setDeleteTag(true);
        videoGroupMapper.updateByPrimaryKey(videoGroup);
        VideoExample example = new VideoExample();
        VideoExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(groupId);
        List<Video> videos = videoMapper.selectByExample(example);
        for (Video video : videos) {
            video.setDeleteTag(true);
            videoMapper.updateByPrimaryKey(video);
        }
    }

    @Override
    public List<VideoGroup> getAllVideoGroups() {
        return videoGroupMapper.selectByExample(new VideoGroupExample());
    }

    @Override
    public List<VideoGroup> getVideoGroups(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        VideoGroupExample example = new VideoGroupExample();
        VideoGroupExample.Criteria criteria = example.createCriteria();
        Optional.ofNullable(keyword).ifPresent(word -> criteria.andNameLike("%" + keyword + "%"));
        return videoGroupMapper.selectByExample(example);
    }

    @Override
    public VideoGroup getVideoGroup(Integer groupId) {
        return videoGroupMapper.selectByPrimaryKey(groupId);
    }

    @Override
    public void updateVideoGroup(UpdateVideoGroupParam updateVideoGroupParam) {
        VideoGroup videoGroup = Optional.ofNullable(videoGroupMapper.selectByPrimaryKey(updateVideoGroupParam.getId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.videoGroupNoFound"));
        BeanUtils.copyProperties(updateVideoGroupParam, videoGroup);
        videoGroupMapper.updateByPrimaryKey(videoGroup);
    }

    @Override
    public int addVideo(AddVideoParam addVideoParam) {
        VideoExample example = new VideoExample();
        VideoExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(addVideoParam.getName());
        Optional.ofNullable(videoMapper.selectByExample(example))
                .orElseThrow(() -> new ApiRuntimeException("exception.sameNameExisted"));
        Video video = new Video();
        BeanUtils.copyProperties(addVideoParam, video);
        video.setUpdateTime(new Date());
        videoMapper.insert(video);
        return video.getId();
    }

    @Override
    public void deleteVideo(Integer videoId) {
        Video video = Optional.ofNullable(videoMapper.selectByPrimaryKey(videoId))
                .orElseThrow(() -> new ApiRuntimeException("exception.videoGroupNoFound"));
        video.setDeleteTag(true);
        videoMapper.updateByPrimaryKey(video);
    }

    @Override
    public List<Video> getAllVideo() {
        return videoMapper.selectByExample(new VideoExample());
    }

    @Override
    public List<Video> getVideos(Integer groupId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        VideoExample example = new VideoExample();
        VideoExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(groupId);
        return videoMapper.selectByExample(example);
    }

    @Override
    public Video getVideo(Integer videoId) {
        return videoMapper.selectByPrimaryKey(videoId);
    }

    @Override
    public void updateVideo(UpdateVideoParam updateVideoParam) {
        Video video = Optional.ofNullable(videoMapper.selectByPrimaryKey(updateVideoParam.getId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.videoNoFound"));
        BeanUtils.copyProperties(updateVideoParam, video);
        video.setUpdateTime(new Date());
        videoMapper.updateByPrimaryKey(video);
    }

    @Override
    public int addVideoType(AddVideoTypeParam addVideoTypeParam) {
        VideoType videoType = new VideoType();
        BeanUtils.copyProperties(addVideoTypeParam, videoType);
        videoTypeMapper.insert(videoType);
        return videoType.getId();
    }

    @Override
    public void updateVideoType(UpdateVideoTypeParam updateVideoTypeParam) {
        VideoType videoType = Optional.ofNullable(videoTypeMapper.selectByPrimaryKey(updateVideoTypeParam.getId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.videoTypeNoFound"));
        BeanUtils.copyProperties(updateVideoTypeParam, videoType);
        videoTypeMapper.updateByPrimaryKey(videoType);
    }

    @Override
    public void deleteVideoType(Integer typeId) {
        VideoType videoType = Optional.ofNullable(videoTypeMapper.selectByPrimaryKey(typeId))
                .orElseThrow(() -> new ApiRuntimeException("exception.videoTypeNoFound"));
        videoType.setDeleteTag(true);
        videoTypeMapper.updateByPrimaryKey(videoType);
    }

    @Override
    public List<VideoType> getAllVideoType() {
        return videoTypeMapper.selectByExample(new VideoTypeExample());
    }

    @Override
    public List<VideoType> getVideoTypes(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        VideoTypeExample example = new VideoTypeExample();
        VideoTypeExample.Criteria criteria = example.createCriteria();
        Optional.ofNullable(keyword).ifPresent(word -> criteria.andNameLike("%" + keyword + "%"));
        return videoTypeMapper.selectByExample(example);
    }

    @Override
    public VideoType getVideoType(Integer typeId) {
        return videoTypeMapper.selectByPrimaryKey(typeId);
    }

    @Override
    public List<VideoType> getVideoTypeByGroupId(Integer groupId) {
        return videoServiceMapper.selectVideoTypeByGroupId(groupId);
    }

    @Override
    public int addVideoTag(AddVideoTagParam addVideoTagParam) {
        VideoTag videoTag = new VideoTag();
        BeanUtils.copyProperties(addVideoTagParam, videoTag);
        videoTagMapper.insert(videoTag);
        return videoTag.getId();
    }

    @Override
    public void updateVideoTag(UpdateVideoTagParam updateVideoTagParam) {
        VideoTag videoTag = Optional.ofNullable(videoTagMapper.selectByPrimaryKey(updateVideoTagParam.getId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.videoTagNoFound"));
        BeanUtils.copyProperties(updateVideoTagParam, videoTag);
        videoTagMapper.updateByPrimaryKey(videoTag);
    }

    @Override
    public void deleteVideoTag(Integer typeId) {
        VideoTag videoTag = Optional.ofNullable(videoTagMapper.selectByPrimaryKey(typeId))
                .orElseThrow(() -> new ApiRuntimeException("exception.videoTagNoFound"));
        videoTag.setDeleteTag(true);
        videoTagMapper.updateByPrimaryKey(videoTag);
    }

    @Override
    public List<VideoTag> getAllVideoTag() {
        return videoTagMapper.selectByExample(new VideoTagExample());
    }

    @Override
    public List<VideoTag> getVideoTags(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        VideoTagExample example = new VideoTagExample();
        VideoTagExample.Criteria criteria = example.createCriteria();
        Optional.ofNullable(keyword).ifPresent(word -> criteria.andNameLike("%" + keyword + "%"));
        return videoTagMapper.selectByExample(example);
    }

    @Override
    public VideoTag getVideoTag(Integer typeId) {
        return videoTagMapper.selectByPrimaryKey(typeId);
    }

    @Override
    public List<VideoTag> getVideoTagByGroupId(Integer groupId) {
        return videoServiceMapper.selectVideoTagByGroupId(groupId);
    }

    @Override
    public void addVideoGroupType(VideoGroupRelateDto videoGroupRelateDto) {
        Optional.ofNullable(videoGroupMapper.selectByPrimaryKey(videoGroupRelateDto.getGroupId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.videoGroupNoFound"));
        Optional.ofNullable(videoTypeMapper.selectByPrimaryKey(videoGroupRelateDto.getRelateId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.videoTypeNoFound"));
        VideoGroupType videoGroupType = new VideoGroupType();
        BeanUtils.copyProperties(videoGroupRelateDto, videoGroupType);
        videoGroupTypeMapper.insert(videoGroupType);
    }

    @Override
    public void deleteVideoGroupType(VideoGroupRelateDto videoGroupRelateDto) {
        videoGroupTypeMapper.deleteByPrimaryKey(videoGroupRelateDto.getGroupId(), videoGroupRelateDto.getRelateId());
    }

    @Override
    public void addVideoGroupTag(VideoGroupRelateDto videoGroupRelateDto) {
        Optional.ofNullable(videoGroupMapper.selectByPrimaryKey(videoGroupRelateDto.getGroupId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.videoGroupNoFound"));
        Optional.ofNullable(videoTagMapper.selectByPrimaryKey(videoGroupRelateDto.getRelateId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.videoTagNoFound"));
        VideoGroupTag videoGroupTag = new VideoGroupTag();
        BeanUtils.copyProperties(videoGroupRelateDto, videoGroupTag);
        videoGroupTagMapper.insert(videoGroupTag);
    }

    @Override
    public void deleteVideoGroupTag(VideoGroupRelateDto videoGroupRelateDto) {
        videoGroupTagMapper.deleteByPrimaryKey(videoGroupRelateDto.getGroupId(), videoGroupRelateDto.getRelateId());
    }
}
