package com.example.food_delivery.configs;


import com.example.food_delivery.utils.JwtTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Фильтр через который проходит запрос для дальнейшей работы,
 * каждый раз как будет сделан запрос, фильтр сам будет проверять актуальность данных без запроса в БД и
 * перекладывать эти данные в контекст вызова
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils; // основной инструмент для работы с токеном

    @Override
    // встраиваемся в цепочку фильтров Spring
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization"); // достаем значение из HEAD с полем Authorization
        String username = null;
        String jwt = null;
        if (authHeader != null) { // проверяем есть ли такой заголовок или нет // && authHeader.startsWith("Bearer ")
            jwt = authHeader; // достаем токен
            try {
                username = jwtTokenUtils.getUsername(jwt); // достаем имя пользователя из токена
            } catch (ExpiredJwtException e) {
                log.debug("Время жизни токена вышло"); // проверяем время жизни токена
            } catch (SignatureException e) {
                log.debug("Подпись неправильная"); // проверяем подпись у токена
            }
        }
        // проверяем что в контексте ничего нет и пользователь такой существует (для того чтобы не ходить каждый раз в БД)
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    username, // ложем username
                    null, // не ложем пароль
                    jwtTokenUtils.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()) // ложем лист ролей
            );

            // ложем в контекст наши данные
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }
}
