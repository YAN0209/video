package com.yan.video.admin.bo;

import com.yan.video.admin.dao.UserServiceMapper;
import com.yan.video.entity.Permission;
import com.yan.video.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户封装类
 *
 * @author likaiyan
 * @date 2020/7/7 11:28 上午
 */
@Getter
@Setter
public class AdminUserDetails implements UserDetails {

    private static UserServiceMapper userServiceMapper;

    private User user;

    private List<Permission> permissionList;

    public AdminUserDetails(User user, List<Permission> permissionList) {
        this.user = user;
        this.permissionList = permissionList;
    }

    public static UserServiceMapper getUserServiceMapper() {
        return userServiceMapper;
    }

    public static void setUserServiceMapper(UserServiceMapper userServiceMapper) {
        AdminUserDetails.userServiceMapper = userServiceMapper;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Permission> permissionList = userServiceMapper.selectPermissionByUserId(user.getId());
        if (!CollectionUtils.isEmpty(permissionList)) {
            return permissionList.stream().map(permission -> new SimpleGrantedAuthority(
                    permission.getId() + ":" + permission.getName())).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isLockTag();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isLockTag();
    }
}
