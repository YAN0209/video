package com.yan.video.aria2.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author likaiyan
 * @date 2020/7/1 3:14 下午
 */
@Getter
@Setter
public class AddTaskParam {

    @NotEmpty
    @ApiModelProperty(value = "视频id")
    private Integer videoId;

    @NotBlank
    @ApiModelProperty(value = "下载地址/磁力链接")
    private String url;

}
