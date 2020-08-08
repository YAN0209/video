package com.yan.video.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author likaiyan
 * @date 2020/6/26 11:08 上午
 */
@Getter
@Setter
public class RolePermissionRelateDto {

    @NotNull
    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @NotNull
    @ApiModelProperty(value = "权限id")
    private Integer permissionId;

}
