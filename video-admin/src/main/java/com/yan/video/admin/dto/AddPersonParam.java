package com.yan.video.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author likaiyan
 * @date 2020/6/13 8:57 下午
 */
@Getter
@Setter
public class AddPersonParam {

    @NotBlank
    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "0未知 1男 2女")
    private Integer sex;

    @ApiModelProperty(value = "所在地")
    private String homePlace;

    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    @ApiModelProperty(value = "职业")
    private String occupation;

    @ApiModelProperty(value = "别名")
    private String alias;

    private String imdbId;

}
