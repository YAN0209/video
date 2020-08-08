package com.yan.video.exception;

import com.yan.video.common.ResponseCode;
import com.yan.video.common.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

/**
 * @author likaiyan
 * @date 2020/4/1 10:30 上午
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private MessageSource messageSource;

    @ResponseBody
    @ExceptionHandler(value = ApiRuntimeException.class)
    public ResponseResult apiRuntimeExceptionHandler(ApiRuntimeException ex) {
        return ResponseResult.exception(ex.getResponseCode(), messageSource.getMessage(ex.getMessage(), null, ex.getMessage(), Locale.CHINESE));
    }

    @ResponseBody
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseResult usernameNotFoundException(UsernameNotFoundException ex) {
        return new ResponseResult(ResponseCode.FAILED, messageSource.getMessage(ex.getMessage(), null, Locale.CHINESE), null);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseResult ExceptionHandler(Exception ex) {
        LOGGER.error(ex.getMessage());
        return ResponseResult.exception(ResponseCode.FAILED, ex.getMessage());
    }
}
