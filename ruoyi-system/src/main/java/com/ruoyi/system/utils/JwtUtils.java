package com.ruoyi.system.utils;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.system.domain.Wxuser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类（适配若依，用于生成、验证、解析JWT令牌）
 */
@Component
public class JwtUtils {

    /**
     * JWT签名密钥（配置在application.yml中，需保密，建议使用随机字符串）
     */

    private String secret="abcdefghijklmnopqrstuvwxy";

    /**
     * JWT过期时间（单位：秒，配置在application.yml中，默认2小时）
     */

    private long expireTime=172800;

    /**
     * 生成JWT令牌（包含登录用户信息）
     * @param
     * @return JWT令牌
     */
    public String generateToken(Wxuser wxuser) {
        // 1. 设置JWT的过期时间
        Date expireDate = new Date(System.currentTimeMillis() + expireTime * 1000);

        // 2. 构建JWT的payload（载荷），存储用户核心信息
        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", wxuser.getId()); // 用户ID


        // 3. 生成JWT令牌
        return Jwts.builder()
                .setClaims(claims) // 设置载荷
                 // 设置主题（通常为用户名）
                .setIssuedAt(new Date()) // 设置签发时间
                .setExpiration(expireDate) // 设置过期时间
                .signWith(SignatureAlgorithm.HS256, secret) // 使用HS256算法签名，传入签名密钥
                .compact(); // 生成最终的JWT字符串
    }

    /**
     * 解析JWT令牌，获取载荷中的所有信息
     * @param token JWT令牌
     * @return 载荷Claims对象
     */
    public Claims parseToken(String token) {
        if (!StringUtils.hasText(token)) {
            return null;
        }
        try {
            // 解析令牌，验证签名和过期时间
            return Jwts.parser()
                    .setSigningKey(secret) // 设置签名密钥
                    .parseClaimsJws(token) // 解析JWT
                    .getBody(); // 获取载荷信息
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
            // 解析失败（令牌无效、过期、签名错误等），返回null
            return null;
        }
    }

    /**
     * 验证JWT令牌是否有效
     * @param token JWT令牌
     * @return true-有效，false-无效
     */
    public boolean validateToken(String token) {
        Claims claims = parseToken(token);
        if (claims == null) {
            return false;
        }
        // 检查令牌是否过期（过期时间是否在当前时间之后）
        return !claims.getExpiration().before(new Date());
    }

    /**
     * 从JWT令牌中获取登录用户ID
     * @param token JWT令牌
     * @return 用户ID
     */
    public Long getUserIdFromToken(String token) {
        Claims claims = parseToken(token);
        if (claims == null) {
            return null;
        }
        return Long.valueOf(claims.get("userId").toString());
    }

    /**
     * 从JWT令牌中获取用户名
     * @param token JWT令牌
     * @return 用户名
     */
    public String getUserNameFromToken(String token) {
        Claims claims = parseToken(token);
        if (claims == null) {
            return null;
        }
        // 两种方式：从subject获取，或从载荷中获取
        return claims.getSubject();
        // 或 return claims.get("userName").toString();
    }

    /**
     * 从JWT令牌中获取用户部门ID
     * @param token JWT令牌
     * @return 部门ID
     */
    public Long getDeptIdFromToken(String token) {
        Claims claims = parseToken(token);
        if (claims == null) {
            return null;
        }
        return Long.valueOf(claims.get("deptId").toString());
    }

    /**
     * 从JWT令牌中获取用户权限列表
     * @param token JWT令牌
     * @return 权限列表
     */
    @SuppressWarnings("unchecked")
    public String getAuthoritiesFromToken(String token) {
        Claims claims = parseToken(token);
        if (claims == null) {
            return null;
        }
        return claims.get("authorities").toString();
    }

    /**
     * 刷新JWT令牌（令牌未过期时，生成新的令牌，延长过期时间）
     * @param token 原JWT令牌
     * @return 新的JWT令牌
     */
    public String refreshToken(String token) {
        if (!validateToken(token)) {
            return null; // 原令牌无效，无法刷新
        }
        Claims claims = parseToken(token);
        // 重置签发时间和过期时间
        claims.setIssuedAt(new Date());
        claims.setExpiration(new Date(System.currentTimeMillis() + expireTime * 1000));
        // 重新生成令牌
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}