package com.example.food_delivery.exceptions;

import lombok.Data;

import java.util.Date;

/**
 * Обработка ошибок при проверке логина и пароля
 */
@Data
public class AppError {
    private int status; // статус ошибки
    private String message; // сообщение об ошибке
    private Date timestamp; // время ошибки

    public AppError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
