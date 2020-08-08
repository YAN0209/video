package com.yan.video.mapper;

import com.yan.video.entity.VideoGroup;
import com.yan.video.entity.VideoGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoGroupMapper {
    long countByExample(VideoGroupExample example);

    int deleteByExample(VideoGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoGroup record);

    int insertSelective(VideoGroup record);

    List<VideoGroup> selectByExample(VideoGroupExample example);

    VideoGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoGroup record, @Param("example") VideoGroupExample example);

    int updateByExample(@Param("record") VideoGroup record, @Param("example") VideoGroupExample example);

    int updateByPrimaryKeySelective(VideoGroup record);

    int updateByPrimaryKey(VideoGroup record);
}