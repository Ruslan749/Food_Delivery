package com.example.food_delivery.dtos;

import lombok.Data;

/**
 * DTOMenuList для регистрации пользователя в сети
 */
@Data
public class RegistrationUserDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}
