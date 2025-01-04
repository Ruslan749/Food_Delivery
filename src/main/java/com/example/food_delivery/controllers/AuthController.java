package com.example.food_delivery.controllers;

import com.example.food_delivery.dtos.JwtRequest;
import com.example.food_delivery.dtos.RegistrationUserDto;
import com.example.food_delivery.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


/**
 * Контроллер аутентификации и выдача токена пользователю
 */
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest, HttpServletResponse response) {
        return authService.createAuthToken(authRequest, response);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }
}