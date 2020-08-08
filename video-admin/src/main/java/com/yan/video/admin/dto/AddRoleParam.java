package com.yan.video.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author likaiyan
 * @date 2020/6/26 11:05 上午
 */
@Getter
@Setter
public class AddRoleParam {

    @NotBlank
    @ApiModelProperty(value = "角色名")
    private String name;

}
