package com.yan.video.admin.service;

import com.yan.video.admin.dto.AddPersonParam;
import com.yan.video.admin.dto.PersonRoleDto;
import com.yan.video.admin.dto.UpdatePersonParam;
import com.yan.video.entity.Person;
import com.yan.video.entity.VideoGroup;

import java.util.List;

/**
 * 视频人员维护
 *
 * @author likaiyan
 * @date 2020/6/8 6:01 下午
 */
public interface PersonService {

    /**
     * 添加人员
     *
     * @param person
     * @return
     */
    int addPerson(AddPersonParam person);

    /**
     * 删除人员
     *
     * @param personId
     */
    void deletePerson(Integer personId);

    /**
     * 获取所有人员
     *
     * @return
     */
    List<Person> getAllPerson();

    /**
     * 搜索人员
     *
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Person> getPersons(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 获取人员
     * @param personId 人员id
     * @return 人员实体
     */
    Person getPerson(Integer personId);

    /**
     * 更新人员信息
     *
     * @param person
     */
    void updatePerson(UpdatePersonParam person);

    /**
     * 添加演员
     *
     * @param personRoleDto
     */
    void addActor(PersonRoleDto personRoleDto);

    /**
     * 删除演员
     *
     * @param personRoleDto
     */
    void deleteActor(PersonRoleDto personRoleDto);

    List<VideoGroup> getVideoGroupByActor(Integer personId);

    List<Person> getActorByVideoGroupId(Integer groupId);

    /**
     * 添加作家
     *
     * @param personRoleDto
     */
    void addWriter(PersonRoleDto personRoleDto);

    /**
     * 删除作家
     *
     * @param personRoleDto
     */
    void deleteWriter(PersonRoleDto personRoleDto);

    List<VideoGroup> getVideoGroupByWriter(Integer personId);

    List<Person> getWriterByVideoGroupId(Integer groupId);

    /**
     * 添加导演
     *
     * @param personRoleDto
     */
    void addDirector(PersonRoleDto personRoleDto);

    /**
     * 删除导演
     *
     * @param personRoleDto
     */
    void deleteDirector(PersonRoleDto personRoleDto);

    List<VideoGroup> getVideoGroupByDirector(Integer personId);

    List<Person> getDirectorByVideoGroupId(Integer groupId);


}