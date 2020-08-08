package com.yan.video.security.service;

import com.yan.video.security.dto.LoginParam;
import com.yan.video.security.dto.RegisterParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author likaiyan
 * @date 2020/7/7 2:33 下午
 */
public interface AuthService {

    String login(LoginParam loginParam);

    void register(RegisterParam registerParam);

    String refreshToken(HttpServletRequest request);

    void sendCheckCode(String email);

}