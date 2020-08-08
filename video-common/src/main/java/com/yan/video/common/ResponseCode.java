package com.yan.video.common;

import lombok.Getter;

/**
 * @author likaiyan
 * @date 2020/4/1 9:55 上午
 */
@Getter
public enum ResponseCode {

    SUCCESS(200, "response.success"),
    FAILED(500, "response.failed"),
    VALIDATE_FAILED(400, "response.validateFailed"),
    UNAUTHORIZED(401, "response.unauthorized"),
    FORBIDDEN(403, "response.forbidden");

    private long code;

    private String message;

    ResponseCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

}
