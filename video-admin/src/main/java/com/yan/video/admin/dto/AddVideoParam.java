package com.yan.video.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author likaiyan
 * @date 2020/6/11 9:42 下午
 */
@Getter
@Setter
public class AddVideoParam {

    @NotBlank
    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "别名")
    private String alias;

    @NotEmpty
    @ApiModelProperty(value = "视频组id")
    private Integer groupId;

    @ApiModelProperty(value = "锁定 0未锁定 1已锁定 默认0")
    private boolean lockTag;

}
