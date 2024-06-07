package com.example.food_delivery.dtos;

import lombok.Data;

/**
 * DTOMenuList для формирования токена(для получения данных от пользователя)
 */
@Data
public class JwtRequest {
    private String username;
    private String password;
}
