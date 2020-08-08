package com.yan.video.admin.service;

import com.yan.video.admin.dto.*;
import com.yan.video.entity.Video;
import com.yan.video.entity.VideoGroup;
import com.yan.video.entity.VideoTag;
import com.yan.video.entity.VideoType;

import java.util.List;

/**
 * 视频相关服务
 *
 * @author likaiyan
 * @date 2020/6/8 5:23 下午
 */
public interface VideoService {

    /**
     * 增加视频组
     * @param addVideoGroupParam 视频组传输类
     * @return 视频组id
     */
    int addVideoGroup(AddVideoGroupParam addVideoGroupParam);

    /**
     * 删除视频组
     *
     * @param groupId 视频组id
     */
    void deleteVideoGroup(Integer groupId);

    /**
     * 获取所有视频组
     *
     * @return 视频组列表
     */
    List<VideoGroup> getAllVideoGroups();

    /**
     * 获取视频组列表
     *
     * @param keyword  关键字
     * @param pageNum  页码
     * @param pageSize 每页数据量
     * @return 视频组列表
     */
    List<VideoGroup> getVideoGroups(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 获取视频组详情
     * @param groupId 视频组id
     * @return 视频组
     */
    VideoGroup getVideoGroup(Integer groupId);

    /**
     * 更新视频组
     * @param updateVideoGroupParam 视频组传输类
     */
    void updateVideoGroup(UpdateVideoGroupParam updateVideoGroupParam);

    /**
     * 添加视频
     * @param addVideoParam 视频传输类
     * @return 视频id
     */
    int addVideo(AddVideoParam addVideoParam);

    /**
     * 删除视频
     * @param videoId 视频id
     */
    void deleteVideo(Integer videoId);

    /**
     * 获取所有视频
     *
     * @return 视频列表
     */
    List<Video> getAllVideo();

    /**
     * 获取视频
     *
     * @param groupId  视频组id
     * @param pageNum  页码
     * @param pageSize 每页数据量
     * @return 视频列表
     */
    List<Video> getVideos(Integer groupId, Integer pageNum, Integer pageSize);

    /**
     * 视频详情
     * @param videoId 视频id
     * @return 视频详情
     */
    Video getVideo(Integer videoId);

    /**
     * 更新视频
     * @param updateVideoParam 视频传输类
     */
    void updateVideo(UpdateVideoParam updateVideoParam);

    /**
     * 添加视频分类
     * @param addVideoTypeParam 视频分类传输类
     * @return 分类id
     */
    int addVideoType(AddVideoTypeParam addVideoTypeParam);

    /**
     * 更新视频分类
     * @param updateVideoTypeParam 视频传输类
     */
    void updateVideoType(UpdateVideoTypeParam updateVideoTypeParam);

    /**
     * 删除视频分类
     * @param typeId 分类id
     */
    void deleteVideoType(Integer typeId);

    /**
     * 获取所有视频类型
     * @return 视频类型列表
     */
    List<VideoType> getAllVideoType();

    /**
     * 获取视频类型
     * @param keyword 关键字
     * @param pageNum 页码
     * @param pageSize 每页数据量
     * @return 视频类型列表
     */
    List<VideoType> getVideoTypes(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 获取视频类型详情
     * @param typeId 视频类型id
     * @return 视频类型实体
     */
    VideoType getVideoType(Integer typeId);

    /**
     * 根据视频组id获取视频类型
     * @param groupId 视频组id
     * @return 视频类型列表
     */
    List<VideoType> getVideoTypeByGroupId(Integer groupId);

    /**
     * 添加视频标签
     * @param addVideoTagParam 视频标签传输类
     * @return 标签id
     */
    int addVideoTag(AddVideoTagParam addVideoTagParam);

    /**
     * 更新视频标签
     * @param updateVideoTagParam 视频标签传输类
     */
    void updateVideoTag(UpdateVideoTagParam updateVideoTagParam);

    /**
     * 删除视频标签
     * @param typeId 标签id
     */
    void deleteVideoTag(Integer typeId);

    /**
     * 获取所有视频标签
     * @return 标签列表
     */
    List<VideoTag> getAllVideoTag();

    /**
     * 获取视频标签
     * @param keyword 关键字
     * @param pageNum 页码
     * @param pageSize 每页数据量
     * @return 标签列表
     */
    List<VideoTag> getVideoTags(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 获取标签详情
     * @param typeId 标签id
     * @return 标签实体
     */
    VideoTag getVideoTag(Integer typeId);

    /**
     * 根据视频id获取视频标签
     * @param groupId 视频组id
     * @return 标签列表
     */
    List<VideoTag> getVideoTagByGroupId(Integer groupId);

    /**
     * 视频组关联视频类型
     * @param videoGroupRelateDto 关联传输类
     */
    void addVideoGroupType(VideoGroupRelateDto videoGroupRelateDto);

    /**
     * 视频组删除关联视频类型
     * @param videoGroupRelateDto 关联传输类
     */
    void deleteVideoGroupType(VideoGroupRelateDto videoGroupRelateDto);

    /**
     * 视频组关联视频标签
     * @param videoGroupRelateDto 关联传输类
     */
    void addVideoGroupTag(VideoGroupRelateDto videoGroupRelateDto);

    /**
     * 视频组删除关联视频标签
     * @param videoGroupRelateDto 关联传输类
     */
    void deleteVideoGroupTag(VideoGroupRelateDto videoGroupRelateDto);
}