package com.yan.video.mapper;

import com.yan.video.entity.VideoType;
import com.yan.video.entity.VideoTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoTypeMapper {
    long countByExample(VideoTypeExample example);

    int deleteByExample(VideoTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoType record);

    int insertSelective(VideoType record);

    List<VideoType> selectByExample(VideoTypeExample example);

    VideoType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoType record, @Param("example") VideoTypeExample example);

    int updateByExample(@Param("record") VideoType record, @Param("example") VideoTypeExample example);

    int updateByPrimaryKeySelective(VideoType record);

    int updateByPrimaryKey(VideoType record);
}