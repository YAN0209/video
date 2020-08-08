package com.yan.video.admin.dao;

import com.yan.video.entity.Person;
import com.yan.video.entity.VideoGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author likaiyan
 * @date 2020/6/10 2:19 下午
 */
@Mapper
public interface PersonServiceMapper {

    List<VideoGroup> selectVideoGroupByActorId(@Param("actorId") Integer actorId);

    List<Person> selectActorByVideoGroupId(@Param("groupId") Integer groupId);

    List<VideoGroup> selectVideoGroupByWriterId(@Param("writerId") Integer writerId);

    List<Person> selectWriterByVideoGroupId(@Param("groupId") Integer groupId);

    List<VideoGroup> selectVideoGroupByDirector(@Param("directorId") Integer directorId);

    List<Person> selectDirectorByVideoGroupId(@Param("groupId") Integer groupId);

}