package com.example.food_delivery.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
/**
    Настройка JWT токена
 */
@Component
public class JwtTokenUtils {

    private String userId = "jti";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;

    // метод, который позволяет из пользователя создать токен
    public String generateToken(UserDetails userDetails, Long userId) {
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities().stream() // получаем список прав доступа
                .map(GrantedAuthority::getAuthority) // преобразовываем их к строке
                .collect(Collectors.toList());
        claims.put("roles", rolesList); // ложем в список

        // настройка времяни токена
        Date issuedDate = new Date(); // когда создан
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime.toMillis()); // когда истечет время пользования

        // собираем токен
        return Jwts.builder()
                .setClaims(claims) // полезная нагрузка
                .setSubject(userDetails.getUsername())
                .setId(userId.toString())
                .setIssuedAt(issuedDate) // время выпуска
                .setExpiration(expiredDate) // на сколько выдан токен
                .signWith(SignatureAlgorithm.HS256, secret) // чем подписать
                .compact(); // собираем токен
    }

    /**
     *  Разбираем приходящий токен на куски
     */


    // из токена получаем имя пользователя
    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Long getUserId(String token) { return Long.parseLong(getAllClaimsFromToken(token).get(userId, String.class)); }
    // получаем роли из токена
    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

    // получаем данные из токена
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
