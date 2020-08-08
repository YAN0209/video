package com.yan.video.admin.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author likaiyan
 * @date 2020/6/9 10:11 上午
 */
@Getter
@Setter
public class PersonRoleDto {

    /**
     * 视频组id
     */
    @NotEmpty
    private Integer groupId;

    /**
     * 人员id
     */
    @NotEmpty
    private Integer personId;

    public PersonRoleDto() {
    }
}
