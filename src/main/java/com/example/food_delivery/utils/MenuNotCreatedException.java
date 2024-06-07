package com.example.food_delivery.utils;

public class MenuNotCreatedException extends  RuntimeException{
    public MenuNotCreatedException(String msg){
        super(msg);
    }
}
