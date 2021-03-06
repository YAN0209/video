package com.yan.video.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author likaiyan
 * @date 2020/6/26 11:06 上午
 */
@Getter
@Setter
public class UpdateRoleParam extends AddRoleParam{

    @NotNull
    @ApiModelProperty(value = "主键")
    private Integer id;

}
