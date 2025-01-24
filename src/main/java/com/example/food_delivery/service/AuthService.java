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

import javax.servlet.http.HttpServletResponse;


/**
 * Сервис для аунтентификации и выдачи токена
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager; // менеджер, который проверят аутентификацию пользователя в сети

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails, userService.getUserId(userDetails.getUsername()));
        response.setHeader("Authorization", token);
        return ResponseEntity.ok(userService.findByUsername(authRequest.getUsername()));
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

    public ResponseEntity<User> updateUser(@RequestBody UserDto userDto) {
        if (userService.findById(userDto.getId()).isPresent()) {
            var user = userService.findById(userDto.getId()).get();
            user.setEmail(userDto.getEmail());
            user.setUsername(userDto.getUsername());
            return ResponseEntity.ok(userService.saveUser(user));
        }
        return ResponseEntity.badRequest().build();
    }
}
