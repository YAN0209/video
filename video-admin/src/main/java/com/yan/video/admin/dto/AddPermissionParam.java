package com.yan.video.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author likaiyan
 * @date 2020/6/26 11:06 上午
 */
@Getter
@Setter
public class AddPermissionParam {

    @NotBlank
    @ApiModelProperty(value = "资源名称")
    private String name;

    @NotBlank
    @ApiModelProperty(value = "资源路径")
    private String uri;

}
