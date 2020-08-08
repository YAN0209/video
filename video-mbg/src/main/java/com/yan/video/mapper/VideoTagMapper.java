package com.yan.video.mapper;

import com.yan.video.entity.VideoTag;
import com.yan.video.entity.VideoTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoTagMapper {
    long countByExample(VideoTagExample example);

    int deleteByExample(VideoTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoTag record);

    int insertSelective(VideoTag record);

    List<VideoTag> selectByExample(VideoTagExample example);

    VideoTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoTag record, @Param("example") VideoTagExample example);

    int updateByExample(@Param("record") VideoTag record, @Param("example") VideoTagExample example);

    int updateByPrimaryKeySelective(VideoTag record);

    int updateByPrimaryKey(VideoTag record);
}