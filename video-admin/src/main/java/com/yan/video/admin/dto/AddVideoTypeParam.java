package com.yan.video.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author likaiyan
 * @date 2020/6/13 10:23 下午
 */
@Getter
@Setter
public class AddVideoTypeParam {

    @NotBlank
    @ApiModelProperty(value = "类型名")
    private String name;

    @ApiModelProperty(value = "锁定 0未锁定 1已锁定")
    private boolean lockTag;

}
