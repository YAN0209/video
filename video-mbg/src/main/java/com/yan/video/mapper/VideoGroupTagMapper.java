package com.yan.video.mapper;

import com.yan.video.entity.VideoGroupTag;
import com.yan.video.entity.VideoGroupTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoGroupTagMapper {
    long countByExample(VideoGroupTagExample example);

    int deleteByExample(VideoGroupTagExample example);

    int deleteByPrimaryKey(@Param("groupId") Integer groupId, @Param("tagId") Integer tagId);

    int insert(VideoGroupTag record);

    int insertSelective(VideoGroupTag record);

    List<VideoGroupTag> selectByExample(VideoGroupTagExample example);

    int updateByExampleSelective(@Param("record") VideoGroupTag record, @Param("example") VideoGroupTagExample example);

    int updateByExample(@Param("record") VideoGroupTag record, @Param("example") VideoGroupTagExample example);
}