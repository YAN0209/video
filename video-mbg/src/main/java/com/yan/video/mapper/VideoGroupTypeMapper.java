package com.yan.video.mapper;

import com.yan.video.entity.VideoGroupType;
import com.yan.video.entity.VideoGroupTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoGroupTypeMapper {
    long countByExample(VideoGroupTypeExample example);

    int deleteByExample(VideoGroupTypeExample example);

    int deleteByPrimaryKey(@Param("groupId") Integer groupId, @Param("typeId") Integer typeId);

    int insert(VideoGroupType record);

    int insertSelective(VideoGroupType record);

    List<VideoGroupType> selectByExample(VideoGroupTypeExample example);

    int updateByExampleSelective(@Param("record") VideoGroupType record, @Param("example") VideoGroupTypeExample example);

    int updateByExample(@Param("record") VideoGroupType record, @Param("example") VideoGroupTypeExample example);
}