package com.yan.video.security.controller;

import com.yan.video.common.ResponseResult;
import com.yan.video.entity.Permission;
import com.yan.video.mapper.PermissionMapper;
import com.yan.video.security.dto.LoginParam;
import com.yan.video.security.dto.RegisterParam;
import com.yan.video.security.service.AuthService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Iterator;
import java.util.Map;

/**
 * @author likaiyan
 * @date 2020/7/7 2:30 下午
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "authController", description = "认证相关接口")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Autowired
    private PermissionMapper mapper;

    @PostMapping("/login")
    public ResponseResult login(@Valid @RequestBody LoginParam loginParam, BindingResult result) {
        return ResponseResult.success(authService.login(loginParam));
    }

    @PostMapping("/register")
    public ResponseResult register(@Valid @RequestBody RegisterParam registerParam, BindingResult result){
        authService.register(registerParam);
        return ResponseResult.success(null);
    }

    @GetMapping("/token/refresh")
    public ResponseResult refreshToken(HttpServletRequest request){
        return ResponseResult.success(authService.refreshToken(request));
    }

    @PostMapping("/checkcode/send")
    public ResponseResult sendCheckCode(@RequestBody String email){
        authService.sendCheckCode(email);
        return ResponseResult.success(null);
    }

    @GetMapping("/allmapping")
    public ResponseResult getAllMapping(){
        Map<RequestMappingInfo, HandlerMethod> map = handlerMapping.getHandlerMethods();
        Iterator<RequestMappingInfo> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next().toString();
            Permission permission = new Permission();
            permission.setDeleteTag(false);
            permission.setLockTag(false);
            permission.setUri(key.substring(key.indexOf("/"), key.lastIndexOf("}")));
            try{
                mapper.insert(permission);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return ResponseResult.success(null);
    }

}
