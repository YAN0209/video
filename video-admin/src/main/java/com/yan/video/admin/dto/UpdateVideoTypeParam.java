package com.yan.video.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author likaiyan
 * @date 2020/6/13 10:22 下午
 */
@Getter
@Setter
public class UpdateVideoTypeParam extends AddVideoTypeParam {

    @NotNull
    @ApiModelProperty(value = "主键")
    private Integer id;

}
