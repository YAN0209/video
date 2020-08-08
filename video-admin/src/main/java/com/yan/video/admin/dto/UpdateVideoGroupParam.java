package com.yan.video.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author likaiyan
 * @date 2020/6/11 9:35 下午
 */
@Getter
@Setter
public class UpdateVideoGroupParam extends AddVideoGroupParam {

    @NotNull
    @ApiModelProperty(value = "主键")
    private Integer id;

}
