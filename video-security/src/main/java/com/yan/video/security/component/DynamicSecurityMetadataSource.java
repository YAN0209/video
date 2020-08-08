package com.yan.video.security.component;

import com.yan.video.common.ResponseCode;
import com.yan.video.entity.Permission;
import com.yan.video.entity.PermissionExample;
import com.yan.video.exception.ApiRuntimeException;
import com.yan.video.mapper.PermissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author likaiyan
 * @date 2020/7/9 2:47 下午
 */
@Component
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicSecurityMetadataSource.class);

    @Autowired
    private PermissionMapper permissionMapper;

    private Map<String, ConfigAttribute> configAttributeMap;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    public DynamicSecurityMetadataSource() {
    }

    @PostConstruct
    public void loadConfigAttributeMap() {
        List<Permission> permissionList = Optional.ofNullable(permissionMapper.selectByExample(new PermissionExample()))
                .orElseGet(ArrayList::new);
        configAttributeMap = permissionList.stream().collect(Collectors.toMap(Permission::getUri,
                permission -> new SecurityConfig(permission.getId() + ":" + permission.getName())));
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if (configAttributeMap == null) {
            loadConfigAttributeMap();
        }
        try {
            handlerMapping.getHandler(((FilterInvocation) o).getRequest());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        String url = (String) ((FilterInvocation) o).getRequest().getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        return configAttributeMap.get(url) ==
                null ? Collections.singletonList(null) : Collections.singletonList(configAttributeMap.get(url));
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<>(configAttributeMap.values());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    private String getUri(String url) {
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException ex) {
            LOGGER.error(ex.getMessage());
            throw new ApiRuntimeException(ResponseCode.FAILED);
        }
        return uri.getPath();
    }
}
