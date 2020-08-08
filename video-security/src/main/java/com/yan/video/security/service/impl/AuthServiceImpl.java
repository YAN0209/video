package com.yan.video.security.service.impl;

import com.yan.video.admin.bo.AdminUserDetails;
import com.yan.video.entity.User;
import com.yan.video.mapper.UserMapper;
import com.yan.video.security.dto.LoginParam;
import com.yan.video.security.dto.RegisterParam;
import com.yan.video.security.service.AuthService;
import com.yan.video.security.util.JwtTokenUtil;
import com.yan.video.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author likaiyan
 * @date 2020/7/7 2:40 下午
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(LoginParam loginParam) {
        AdminUserDetails adminUserDetails = userService.loadUserByLoginName(loginParam.getLoginName());
        if(passwordEncoder.matches(loginParam.getPassword(), adminUserDetails.getPassword())){
            return jwtTokenUtil.generateToken(adminUserDetails);
        }
        return null;
    }

    @Override
    public void register(RegisterParam registerParam) {
        User user = new User();
        BeanUtils.copyProperties(registerParam, user);
        user.setPassword(passwordEncoder.encode(registerParam.getPassword()));
        user.setRegistrationTime(new Date());
        userMapper.insert(user);
    }

    @Override
    public String refreshToken(HttpServletRequest request) {
        String token = jwtTokenUtil.getToken(request);
        return jwtTokenUtil.refreshToken(token);
    }

    @Override
    public void sendCheckCode(String email) {
    }
}
