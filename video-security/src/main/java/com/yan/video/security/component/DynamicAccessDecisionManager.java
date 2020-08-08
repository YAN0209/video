package com.yan.video.security.component;

import com.yan.video.common.ResponseCode;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author likaiyan
 * @date 2020/7/9 11:17 上午
 */
@Component
public class DynamicAccessDecisionManager implements AccessDecisionManager {

    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

    public DynamicAccessDecisionManager() {
    }

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection)
            throws AccessDeniedException, InsufficientAuthenticationException {
        collection = collection.stream().filter(Objects::nonNull).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(collection)) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                for (ConfigAttribute attr : collection) {
                    if (antPathMatcher.match(authority.getAuthority(), attr.getAttribute())) {
                        return;
                    }
                }
            }
        }
        throw new AccessDeniedException(ResponseCode.FORBIDDEN.getMessage());
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
