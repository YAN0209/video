package com.yan.video.admin.dao;

import com.yan.video.entity.VideoTag;
import com.yan.video.entity.VideoType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author likaiyan
 * @date 2020/6/13 11:05 下午
 */
@Mapper
public interface VideoServiceMapper {

    List<VideoType> selectVideoTypeByGroupId(@Param("groupId") Integer groupId);

    List<VideoTag> selectVideoTagByGroupId(@Param("tagId") Integer tagId);

}