package com.yan.video.security.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author likaiyan
 * @date 2020/7/7 2:43 下午
 */
@Getter
@Setter
public class LoginParam {

    @NotBlank
    @ApiModelProperty(value = "登陆名")
    private String loginName;

    @NotBlank
    @ApiModelProperty(value = "密码")
    private String password;

}
