package com.example.food_delivery.dtos;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class MenuDTO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotEmpty(message = "имя не должно быть пустым")
    @Size(min = 5,message = "имя не должно быть меньше 2 символов и больше 30")
    private String nameFood;


    @Min(value = 10,message = "цена не должна быть пустой")
    private int price;

    @NotEmpty(message = "описание не должено быть пустым")
    @Size(min = 5,message = "в описание не соответствие колличества символов")
    private String description;

    private int categories_id;
}
