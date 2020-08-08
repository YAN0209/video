package com.yan.video.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author likaiyan
 * @date 2020/6/13 10:29 下午
 */
@Getter
@Setter
public class AddVideoTagParam {

    @NotBlank
    @ApiModelProperty(value = "标签名")
    private String name;

    @ApiModelProperty(value = "锁定 0未锁定 1已锁定")
    private boolean lockTag;

}
