package com.yan.video.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author likaiyan
 * @date 2020/6/26 11:01 上午
 */
@Getter
@Setter
public class UpdateUserParam extends AddUserParam{

    @NotNull
    @ApiModelProperty(value = "主键")
    private Integer id;

}
