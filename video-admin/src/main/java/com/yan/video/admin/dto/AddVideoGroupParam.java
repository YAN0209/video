package com.yan.video.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author likaiyan
 * @date 2020/6/11 9:34 下午
 */
@Getter
@Setter
public class AddVideoGroupParam {

    @NotBlank
    @ApiModelProperty(value = "视频组名")
    private String name;

    @ApiModelProperty(value = "评分")
    private Float rate;

    @ApiModelProperty(value = "imdbId")
    private String imdbId;

    @ApiModelProperty(value = "别名")
    private String alias;

    @ApiModelProperty(value = "封面")
    private String poster;

    @ApiModelProperty(value = "视频码")
    private String code;

    @ApiModelProperty(value = "锁定 0未锁定 1已锁定")
    private boolean lockTag;

}
