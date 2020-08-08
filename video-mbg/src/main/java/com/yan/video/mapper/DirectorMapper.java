package com.yan.video.mapper;

import com.yan.video.entity.Director;
import com.yan.video.entity.DirectorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DirectorMapper {
    long countByExample(DirectorExample example);

    int deleteByExample(DirectorExample example);

    int deleteByPrimaryKey(@Param("groupId") Integer groupId, @Param("personId") Integer personId);

    int insert(Director record);

    int insertSelective(Director record);

    List<Director> selectByExample(DirectorExample example);

    int updateByExampleSelective(@Param("record") Director record, @Param("example") DirectorExample example);

    int updateByExample(@Param("record") Director record, @Param("example") DirectorExample example);
}