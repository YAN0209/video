package com.yan.video.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.Optional;

/**
 * @author likaiyan
 * @date 2020/4/1 9:49 上午
 */
@Getter
@Setter
public class ResponseResult<T> {

    private static MessageSource messageSource;

    private long code;
    private String message;
    private T data;

    public ResponseResult(ResponseCode responseCode, String message, T data) {
        this.setCode(responseCode.getCode());
        this.setMessage(Optional.ofNullable(message).orElse(responseCode.getMessage()));
        this.setData(data);
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<T>(ResponseCode.SUCCESS, messageSource.getMessage(ResponseCode.SUCCESS.getMessage(), null, Locale.CHINESE), data);
    }

    public static <T> ResponseResult<T> failed(T data) {
        return new ResponseResult<T>(ResponseCode.FAILED, messageSource.getMessage(ResponseCode.FAILED.getMessage(), null, Locale.CHINESE), data);
    }

    public static <T> ResponseResult<T> exception(ResponseCode responseCode, String message) {
        return new ResponseResult<>(responseCode, messageSource.getMessage(message, null, messageSource.getMessage(ResponseCode.FAILED.getMessage(), null, Locale.CHINESE), Locale.CHINESE), null);
    }

    public static <T> ResponseResult<T> forbidden() {
        return new ResponseResult<>(ResponseCode.FORBIDDEN, messageSource.getMessage(ResponseCode.FORBIDDEN.getMessage(), null, Locale.CHINESE), null);
    }

    public static <T> ResponseResult<T> unauthorized() {
        return new ResponseResult<>(ResponseCode.UNAUTHORIZED, messageSource.getMessage(ResponseCode.UNAUTHORIZED.getMessage(), null, Locale.CHINESE), null);
    }

    public static void setMessageSource(MessageSource messageSource) {
        ResponseResult.messageSource = messageSource;
    }
}
