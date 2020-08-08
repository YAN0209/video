package com.yan.video.admin.config;

import com.yan.video.admin.bo.AdminUserDetails;
import com.yan.video.admin.dao.UserServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author likaiyan
 * @date 2020/7/9 10:15 上午
 */
@Component
public class StaticInitializer {

    @Autowired
    private UserServiceMapper userServiceMapper;

    public StaticInitializer() {
    }

    @PostConstruct
    public void init(){
        AdminUserDetails.setUserServiceMapper(userServiceMapper);
    }
}
