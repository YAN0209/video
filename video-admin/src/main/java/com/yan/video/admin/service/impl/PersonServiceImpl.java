package com.yan.video.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.yan.video.admin.dao.PersonServiceMapper;
import com.yan.video.admin.dto.AddPersonParam;
import com.yan.video.admin.dto.PersonRoleDto;
import com.yan.video.admin.dto.UpdatePersonParam;
import com.yan.video.entity.*;
import com.yan.video.exception.ApiRuntimeException;
import com.yan.video.mapper.ActorMapper;
import com.yan.video.mapper.DirectorMapper;
import com.yan.video.mapper.PersonMapper;
import com.yan.video.mapper.WriterMapper;
import com.yan.video.admin.service.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author likaiyan
 * @date 2020/6/8 7:07 下午
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private WriterMapper writerMapper;

    @Autowired
    private DirectorMapper directorMapper;

    @Autowired
    private ActorMapper actorMapper;

    @Autowired
    private PersonServiceMapper personServiceMapper;

    public PersonServiceImpl() {
    }

    @Override
    public int addPerson(AddPersonParam person) {
        Person newPerson = new Person();
        BeanUtils.copyProperties(person, newPerson);
        return personMapper.insert(newPerson);
    }

    @Override
    public void deletePerson(Integer personId) {
        Person person = Optional.ofNullable(personMapper.selectByPrimaryKey(personId))
                .orElseThrow(() -> new ApiRuntimeException("exception.personNoFound"));
        person.setDeleteTag(true);
        personMapper.updateByPrimaryKey(person);
    }

    @Override
    public List<Person> getAllPerson() {
        return personMapper.selectByExample(new PersonExample());
    }

    @Override
    public List<Person> getPersons(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PersonExample example = new PersonExample();
        PersonExample.Criteria criteria = example.createCriteria();
        Optional.ofNullable(keyword).ifPresent(word -> criteria.andNameLike("%" + word + "%"));
        return personMapper.selectByExample(example);
    }

    @Override
    public Person getPerson(Integer personId) {
        return personMapper.selectByPrimaryKey(personId);
    }

    @Override
    public void updatePerson(UpdatePersonParam person) {
        Person newPerson = new Person();
        BeanUtils.copyProperties(person, newPerson);
        personMapper.updateByPrimaryKey(newPerson);
    }

    @Override
    public void addActor(PersonRoleDto personRoleDto) {
        Actor actor = new Actor();
        BeanUtils.copyProperties(personRoleDto, actor);
        actorMapper.insert(actor);
    }

    @Override
    public void deleteActor(PersonRoleDto personRoleDto) {
        ActorExample example = new ActorExample();
        ActorExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(personRoleDto.getGroupId())
                .andPersonIdEqualTo(personRoleDto.getPersonId());
        actorMapper.deleteByExample(example);
    }

    @Override
    public List<VideoGroup> getVideoGroupByActor(Integer personId) {
        return personServiceMapper.selectVideoGroupByActorId(personId);
    }

    @Override
    public List<Person> getActorByVideoGroupId(Integer groupId) {
        return personServiceMapper.selectActorByVideoGroupId(groupId);
    }

    @Override
    public void addWriter(PersonRoleDto personRoleDto) {
        Writer writer = new Writer();
        BeanUtils.copyProperties(personRoleDto, writer);
        writerMapper.insert(writer);
    }

    @Override
    public void deleteWriter(PersonRoleDto personRoleDto) {
        WriterExample example = new WriterExample();
        WriterExample.Criteria criteria = example.createCriteria();
        criteria.andPersonIdEqualTo(personRoleDto.getPersonId())
                .andGroupIdEqualTo(personRoleDto.getGroupId());
        writerMapper.deleteByExample(example);
    }

    @Override
    public List<VideoGroup> getVideoGroupByWriter(Integer personId) {
        return personServiceMapper.selectVideoGroupByWriterId(personId);
    }

    @Override
    public List<Person> getWriterByVideoGroupId(Integer groupId) {
        return personServiceMapper.selectWriterByVideoGroupId(groupId);
    }

    @Override
    public void addDirector(PersonRoleDto personRoleDto) {
        Director director = new Director();
        BeanUtils.copyProperties(personRoleDto, director);
        directorMapper.insert(director);
    }

    @Override
    public void deleteDirector(PersonRoleDto personRoleDto) {
        DirectorExample example = new DirectorExample();
        DirectorExample.Criteria criteria = example.createCriteria();
        criteria.andPersonIdEqualTo(personRoleDto.getPersonId())
                .andGroupIdEqualTo(personRoleDto.getGroupId());
        directorMapper.deleteByExample(example);
    }

    @Override
    public List<VideoGroup> getVideoGroupByDirector(Integer personId) {
        return personServiceMapper.selectVideoGroupByDirector(personId);
    }

    @Override
    public List<Person> getDirectorByVideoGroupId(Integer groupId) {
        return personServiceMapper.selectDirectorByVideoGroupId(groupId);
    }
}
