package com.yan.video.mapper;

import com.yan.video.entity.DownloadTask;
import com.yan.video.entity.DownloadTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DownloadTaskMapper {
    long countByExample(DownloadTaskExample example);

    int deleteByExample(DownloadTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DownloadTask record);

    int insertSelective(DownloadTask record);

    List<DownloadTask> selectByExample(DownloadTaskExample example);

    DownloadTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DownloadTask record, @Param("example") DownloadTaskExample example);

    int updateByExample(@Param("record") DownloadTask record, @Param("example") DownloadTaskExample example);

    int updateByPrimaryKeySelective(DownloadTask record);

    int updateByPrimaryKey(DownloadTask record);
}