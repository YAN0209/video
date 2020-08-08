package com.yan.video.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author likaiyan
 * @date 2020/6/11 10:52 下午
 */
@Component
public class StaticContextInitializer {

    @Autowired
    private MessageSource messageSource;

    @PostConstruct
    public void init(){
        ResponseResult.setMessageSource(messageSource);
    }

}
