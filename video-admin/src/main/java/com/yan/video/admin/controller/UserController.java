package com.yan.video.admin.controller;

import com.yan.video.common.ResponseResult;
import com.yan.video.admin.dto.*;
import com.yan.video.admin.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author likaiyan
 * @date 2020/6/26 9:02 下午
 */
@RestController
@Api(tags = "userController", description = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/add")
    public ResponseResult addUser(@Valid @RequestBody AddUserParam addUserParam, BindingResult result) {
        return ResponseResult.success(userService.addUser(addUserParam));
    }

    @PostMapping("/user/delete")
    public ResponseResult deleteUser(@RequestParam Integer userId) {
        userService.deleteUser(userId);
        return ResponseResult.success(null);
    }

    @PostMapping("/user/update")
    public ResponseResult updateUser(@Valid @RequestBody UpdateUserParam updateUserParam, BindingResult result) {
        userService.updateUser(updateUserParam);
        return ResponseResult.success(null);
    }
    
    @GetMapping("/user/list/all")
    public ResponseResult getAllUser(){
        return ResponseResult.success(userService.getAllUsers());
    }
    
    @GetMapping("/user/list/search")
    public ResponseResult searchUser(@RequestParam String keyword,
                                     @RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "5") Integer pageSize){
        return ResponseResult.success(userService.searchUser(keyword, pageNum, pageSize));
    }
    
    @GetMapping("/user/{userId}")
    public ResponseResult getUser(@PathVariable Integer userId){
        return ResponseResult.success(userService.getUser(userId));
    }

    @PostMapping("/role/add")
    public ResponseResult addRole(@Valid @RequestBody AddRoleParam addRoleParam, BindingResult result) {
        return ResponseResult.success(userService.addRole(addRoleParam));
    }

    @PostMapping("/role/delete")
    public ResponseResult deleteRole(@RequestParam Integer roleId) {
        userService.deleteRole(roleId);
        return ResponseResult.success(null);
    }

    @PostMapping("/role/update")
    public ResponseResult updateRole(@Valid @RequestBody UpdateRoleParam updateRoleParam, BindingResult result) {
        userService.updateRole(updateRoleParam);
        return ResponseResult.success(null);
    }

    @GetMapping("/role/list/all")
    public ResponseResult getAllRole(){
        return ResponseResult.success(userService.getAllRoles());
    }

    @GetMapping("/role/list/search")
    public ResponseResult searchRole(@RequestParam String keyword,
                                     @RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "5") Integer pageSize){
        return ResponseResult.success(userService.searchRole(keyword, pageNum, pageSize));
    }

    @GetMapping("/role/{roleId}")
    public ResponseResult getRole(@PathVariable Integer roleId){
        return ResponseResult.success(userService.getRole(roleId));
    }

    @PostMapping("/permission/add")
    public ResponseResult addPermission(@Valid @RequestBody AddPermissionParam addPermissionParam, BindingResult result) {
        return ResponseResult.success(userService.addPermission(addPermissionParam));
    }

    @PostMapping("/permission/delete")
    public ResponseResult deletePermission(@RequestParam Integer permissionId) {
        userService.deletePermission(permissionId);
        return ResponseResult.success(null);
    }

    @PostMapping("/permission/update")
    public ResponseResult updatePermission(@Valid @RequestBody UpdatePermissionParam updatePermissionParam, BindingResult result) {
        userService.updatePermission(updatePermissionParam);
        return ResponseResult.success(null);
    }

    @GetMapping("/permission/list/all")
    public ResponseResult getAllPermission(){
        return ResponseResult.success(userService.getAllPermissions());
    }

    @GetMapping("/permission/list/search")
    public ResponseResult searchPermission(@RequestParam String keyword,
                                     @RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "5") Integer pageSize){
        return ResponseResult.success(userService.searchPermission(keyword, pageNum, pageSize));
    }

    @GetMapping("/permission/{permissionId}")
    public ResponseResult getPermission(@PathVariable Integer permissionId){
        return ResponseResult.success(userService.getPermission(permissionId));
    }
    
    @PostMapping("/userrole/add")
    public ResponseResult addUserRoleRelation(@Valid @RequestBody UserRoleRelateDto userRoleRelateDto, BindingResult result){
        return ResponseResult.success(userService.addUserRoleRelate(userRoleRelateDto));
    }
    
    @PostMapping("/userrole/delete")
    public ResponseResult deleteUserRoleRelation(@Valid @RequestBody UserRoleRelateDto userRoleRelateDto, BindingResult result){
        userService.deleteUserRoleRelate(userRoleRelateDto);
        return ResponseResult.success(null);
    }

    @PostMapping("/rolepermission/add")
    public ResponseResult addRolePermissionRelation(@Valid @RequestBody RolePermissionRelateDto userRoleRelateDto, BindingResult result){
        return ResponseResult.success(userService.addRolePermissionRelate(userRoleRelateDto));
    }

    @PostMapping("/rolepermission/delete")
    public ResponseResult deleteRolePermissionRelation(@Valid @RequestBody RolePermissionRelateDto userRoleRelateDto, BindingResult result){
        userService.deleteRolePermissionRelate(userRoleRelateDto);
        return ResponseResult.success(null);
    }
    
}
