package com.yan.video.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.yan.video.admin.bo.AdminUserDetails;
import com.yan.video.admin.dao.UserServiceMapper;
import com.yan.video.admin.dto.*;
import com.yan.video.entity.*;
import com.yan.video.exception.ApiRuntimeException;
import com.yan.video.mapper.*;
import com.yan.video.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author likaiyan
 * @date 2020/6/26 11:47 上午
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;

    @Autowired
    private RolePermissionRelationMapper rolePermissionRelationMapper;

    @Autowired
    private UserServiceMapper userServiceMapper;

    @Override
    public int addUser(AddUserParam addUserParam) {
        User user = new User();
        BeanUtils.copyProperties(addUserParam, user);
        user.setRegistrationTime(new Date());
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public void deleteUser(int userId) {
        User user = Optional.ofNullable(userMapper.selectByPrimaryKey(userId))
                .orElseThrow(() -> new ApiRuntimeException("exception.UserNoFound"));
        user.setDeleteTag(true);
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void updateUser(UpdateUserParam updateUserParam) {
        User user = new User();
        BeanUtils.copyProperties(updateUserParam, user);
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public User getUser(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public List<User> searchUser(String keyword, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        Optional.ofNullable(keyword)
                .ifPresent(word -> criteria.andUsernameLike("%" + word + "%"));
        return userMapper.selectByExample(example);
    }

    @Override
    public int addRole(AddRoleParam addRoleParam) {
        Role role = new Role();
        BeanUtils.copyProperties(addRoleParam, role);
        roleMapper.insert(role);
        return role.getId();
    }

    @Override
    public void deleteRole(int roleId) {
        Role role = Optional.ofNullable(roleMapper.selectByPrimaryKey(roleId))
                .orElseThrow(() -> new ApiRuntimeException("exception.RoleNoFound"));
        role.setDeleteTag(true);
        roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public void updateRole(UpdateRoleParam updateRoleParam) {
        Role role = new Role();
        BeanUtils.copyProperties(updateRoleParam, role);
        roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public Role getRole(int roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleMapper.selectByExample(new RoleExample());
    }

    @Override
    public List<Role> searchRole(String keyword, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        Optional.ofNullable(keyword)
                .ifPresent(word -> criteria.andNameLike("%" + word + "%"));
        return roleMapper.selectByExample(example);
    }

    @Override
    public int addPermission(AddPermissionParam addPermissionParam) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(addPermissionParam, permission);
        permissionMapper.insert(permission);
        return permission.getId();
    }

    @Override
    public void deletePermission(int permissionId) {
        Permission permission = Optional.ofNullable(permissionMapper.selectByPrimaryKey(permissionId))
                .orElseThrow(() -> new ApiRuntimeException("exception.PermissionNoFound"));
        permission.setDeleteTag(true);
        permissionMapper.updateByPrimaryKey(permission);
    }

    @Override
    public void updatePermission(UpdatePermissionParam updatePermissionParam) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(updatePermissionParam, permission);
        permissionMapper.updateByPrimaryKey(permission);
    }

    @Override
    public Permission getPermission(int permissionId) {
        return permissionMapper.selectByPrimaryKey(permissionId);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionMapper.selectByExample(new PermissionExample());
    }

    @Override
    public List<Permission> searchPermission(String keyword, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PermissionExample example = new PermissionExample();
        PermissionExample.Criteria criteria = example.createCriteria();
        Optional.ofNullable(keyword)
                .ifPresent(word -> criteria.andNameLike("%" + word + "%"));
        return permissionMapper.selectByExample(example);
    }

    @Override
    public int addUserRoleRelate(UserRoleRelateDto userRoleRelateDto) {
        UserRoleRelation userRoleRelation = new UserRoleRelation();
        BeanUtils.copyProperties(userRoleRelateDto, userRoleRelation);
        return userRoleRelationMapper.insert(userRoleRelation);
    }

    @Override
    public void deleteUserRoleRelate(UserRoleRelateDto userRoleRelateDto) {
        userRoleRelationMapper.deleteByPrimaryKey(userRoleRelateDto.getUserId(), userRoleRelateDto.getRoleId());
    }

    @Override
    public int addRolePermissionRelate(RolePermissionRelateDto rolePermissionRelateDto) {
        RolePermissionRelation rolePermissionRelation = new RolePermissionRelation();
        BeanUtils.copyProperties(rolePermissionRelateDto, rolePermissionRelation);
        return rolePermissionRelationMapper.insert(rolePermissionRelation);
    }

    @Override
    public void deleteRolePermissionRelate(RolePermissionRelateDto permissionRelateDto) {
        rolePermissionRelationMapper.deleteByPrimaryKey(permissionRelateDto.getRoleId(), permissionRelateDto.getPermissionId());
    }

    @Override
    public AdminUserDetails loadUserByUserId(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if(user != null){
            List<Permission> permissionList = userServiceMapper.selectPermissionByUserId(userId);
            return new AdminUserDetails(user, permissionList);
        }
        throw new UsernameNotFoundException("exception.userNoFound");
    }

    @Override
    public AdminUserDetails loadUserByLoginName(String loginName) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        List<User> users = userMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(users)){
            List<Permission> permissionList = userServiceMapper.selectPermissionByUserId(users.get(0).getId());
            return new AdminUserDetails(users.get(0), permissionList);
        }
        throw new UsernameNotFoundException("exception.userNoFound");
    }
}
