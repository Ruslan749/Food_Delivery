package com.example.food_delivery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {
    // доступ хоть с токеном хоть без
    @GetMapping("/unsecured")
    public String unsecuredData() {
        return "Unsecured data";
    }

    // защищеные данные пользователя после получения токена
    @GetMapping("/secured")
    public String securedData() {
        return "Secured data";
    }
    // данные админа
    @GetMapping("/admin")
    public String adminData() {
        return "Admin data";
    }

    // получение данных текущего пользователя (его имя)
    @GetMapping("/info")
    public String userData(Principal principal) {
        return principal.getName();
    }
}