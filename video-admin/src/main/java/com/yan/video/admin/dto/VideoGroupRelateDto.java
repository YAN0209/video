package com.yan.video.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 视频组 类型 标签 关联 传输类
 * @author likaiyan
 * @date 2020/6/14 10:22 上午
 */
@Getter
@Setter
public class VideoGroupRelateDto {

    @NotNull
    @ApiModelProperty(value = "视频组id")
    private Integer groupId;

    @NotNull
    @ApiModelProperty(value = "视频类型 视频标签 id")
    private Integer relateId;

}
