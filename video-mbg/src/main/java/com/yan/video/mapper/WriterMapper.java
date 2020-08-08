package com.yan.video.mapper;

import com.yan.video.entity.Writer;
import com.yan.video.entity.WriterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WriterMapper {
    long countByExample(WriterExample example);

    int deleteByExample(WriterExample example);

    int deleteByPrimaryKey(@Param("groupId") Integer groupId, @Param("personId") Integer personId);

    int insert(Writer record);

    int insertSelective(Writer record);

    List<Writer> selectByExample(WriterExample example);

    int updateByExampleSelective(@Param("record") Writer record, @Param("example") WriterExample example);

    int updateByExample(@Param("record") Writer record, @Param("example") WriterExample example);
}