package com.yan.video.admin.dao;

import com.yan.video.entity.Permission;
import com.yan.video.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author likaiyan
 * @date 2020/7/7 11:53 上午
 */
@Mapper
public interface UserServiceMapper {

    List<Role> selectRolesByUserId(@Param("userId") Integer userId);

    List<Permission> selectPermissionByUserId(@Param("userId") Integer userId);

}
