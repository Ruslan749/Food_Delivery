package com.example.food_delivery.controllers;


import com.example.food_delivery.dtos.CatalogMenuDTO;
import com.example.food_delivery.entities.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.food_delivery.utils.MenuErrorResponse;
import com.example.food_delivery.utils.MenuNotCreatedException;
import com.example.food_delivery.utils.MenuNotFoundException;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/menu")
public class MenuController {
    private final com.example.food_delivery.service.menuService menuService;

    @Autowired
    public MenuController(com.example.food_delivery.service.menuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping()
    public List<CatalogMenuDTO> getMenu() {
        return Collections.singletonList(menuService.findAll());
    }


    @GetMapping("/{id}")
    public Menu getProductMenuById(@PathVariable("id") int id) {
        return menuService.findOne(id); // Jackson конвертирует в JSON
    }



    @ExceptionHandler
    private ResponseEntity<MenuErrorResponse> handleException(MenuNotFoundException e){
        MenuErrorResponse response = new MenuErrorResponse(
                "продукт с таким id найден",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }




    // ошибка при создании нового блюда в меню
    @ExceptionHandler
    private ResponseEntity<MenuErrorResponse> handleException(MenuNotCreatedException e){
        MenuErrorResponse response = new MenuErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}