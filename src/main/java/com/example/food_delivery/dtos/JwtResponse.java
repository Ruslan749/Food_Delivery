package com.example.food_delivery.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTOMenuList для выдачи токена пользователю(ответ)
 */
@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
}
