package com.yan.video.admin.service;

import com.yan.video.admin.bo.AdminUserDetails;
import com.yan.video.admin.dto.*;
import com.yan.video.entity.Permission;
import com.yan.video.entity.Role;
import com.yan.video.entity.User;

import java.util.List;

/**
 * 用户管理
 * @author likaiyan
 * @date 2020/6/8 5:24 下午
 */
public interface UserService {

    int addUser(AddUserParam addUserParam);

    void deleteUser(int userId);

    void updateUser(UpdateUserParam updateUserParam);

    User getUser(int userId);

    List<User> getAllUsers();

    List<User> searchUser(String keyword, int pageNum, int pageSize);

    int addRole(AddRoleParam addRoleParam);
    
    void deleteRole(int roleId);
    
    void updateRole(UpdateRoleParam updateRoleParam);
    
    Role getRole(int roleId);
    
    List<Role> getAllRoles();
    
    List<Role> searchRole(String keyword, int pageNum, int pageSize);
    
    int addPermission(AddPermissionParam addPermissionParam);
    
    void deletePermission(int permissionId);

    void updatePermission(UpdatePermissionParam updatePermissionParam);

    Permission getPermission(int permissionId);

    List<Permission> getAllPermissions();

    List<Permission> searchPermission(String keyword, int pageNum, int pageSize);

    int addUserRoleRelate(UserRoleRelateDto userRoleRelateDto);

    void deleteUserRoleRelate(UserRoleRelateDto userRoleRelateDto);

    int addRolePermissionRelate(RolePermissionRelateDto rolePermissionRelateDto);

    void deleteRolePermissionRelate(RolePermissionRelateDto rolePermissionRelateDto);

    AdminUserDetails loadUserByUserId(int userId);

    AdminUserDetails loadUserByLoginName(String loginName);

}