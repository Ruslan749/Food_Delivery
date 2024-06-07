package com.example.food_delivery.service;

import com.example.food_delivery.dtos.JwtRequest;
import com.example.food_delivery.dtos.JwtResponse;
import com.example.food_delivery.dtos.RegistrationUserDto;
import com.example.food_delivery.dtos.UserDto;
import com.example.food_delivery.entities.User;
import com.example.food_delivery.exceptions.AppError;
import com.example.food_delivery.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * Сервис для аунтентификации и выдачи токена
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager; // менеджер, который проверят аутентификацию пользователя в сети

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) { // приходят на вход данные (запрос на аутентификацию)
        try {

            // проверка на существование такого пользователя в БД
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        } catch (BadCredentialsException e) {
            // выкидываем созданную нами ошибку в AppError
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);

        }

        // загружаем пользователя по его имени
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        // генерируем токен по данным пользователя userDetails
        String token = jwtTokenUtils.generateToken(userDetails);
        // возвращаем сгенерированный токен со статусом OK
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) { // приходят данные для регистрации
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            // проверка на то что пароли совпадают
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        // проверяем на существование пользователя в базе данных
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем уже существует"), HttpStatus.BAD_REQUEST);
        }

        // создание нового user в БД
        User user = userService.createNewUser(registrationUserDto);
        // возвращаем данные зарегистрированного пользователя
        return ResponseEntity.ok(new UserDto(user.getId(), user.getUsername(), user.getEmail()));
    }
}
