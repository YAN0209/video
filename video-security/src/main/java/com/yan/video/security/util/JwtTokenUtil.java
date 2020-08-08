package com.yan.video.security.util;

import com.yan.video.admin.bo.AdminUserDetails;
import com.yan.video.common.ResponseCode;
import com.yan.video.exception.ApiRuntimeException;
import com.yan.video.admin.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author likaiyan
 * @date 2020/7/6 3:12 下午
 */
@Component
public class JwtTokenUtil {

    @Autowired
    private UserService userService;

    private static final String CLAIM_KEY_SUB = "sub";

    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(getExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String generateToken(AdminUserDetails adminUserDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_SUB, adminUserDetails.getUser().getId());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    public String refreshToken(String oldToken) {
        if (StringUtils.isEmpty(oldToken) || !validateToken(oldToken)) {
            throw new ApiRuntimeException(ResponseCode.VALIDATE_FAILED);
        }
        AdminUserDetails adminUserDetails = userService.loadUserByUserId(getUserIdFromToken(oldToken));
        return generateToken(adminUserDetails);
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return new Date().after(getExpirationDateFromToken(token));
    }

    public Date getExpirationDate() {
        return Date.from(Instant.now().plusMillis(1000 * expiration));
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public Date getCreateDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return (Date) claims.get(CLAIM_KEY_CREATED);
    }

    public Date getExpirationDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    public int getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return (int) claims.get(CLAIM_KEY_SUB);
    }

    public String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(tokenHeader);
        if(StringUtils.isEmpty(bearerToken) || bearerToken.length() < tokenHead.length()){
            return null;
        }
        return bearerToken.substring(tokenHead.length());
    }


}
