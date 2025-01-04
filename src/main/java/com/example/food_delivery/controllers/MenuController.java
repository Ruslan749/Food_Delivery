package com.example.food_delivery.controllers;


import com.example.food_delivery.dtos.CatalogMenuDTO;
import com.example.food_delivery.dtos.MenuDTO;
import com.example.food_delivery.entities.Menu;
import com.example.food_delivery.service.menuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.food_delivery.utils.MenuErrorResponse;
import com.example.food_delivery.utils.MenuNotCreatedException;
import com.example.food_delivery.utils.MenuNotFoundException;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/menu")
public class MenuController {
    private final menuService menuService;

    @Autowired
    public MenuController(menuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping()
    public CatalogMenuDTO getMenu() {
        return menuService.findAll();
    }


    @GetMapping("/{id}")
    public Menu getProductMenuById(@PathVariable("id") int id) {
        return menuService.findOne(id); // Jackson конвертирует в JSON
    }

    @PostMapping("/addProduct")
    public  ResponseEntity<MenuDTO> addProductMenu(@RequestBody  MenuDTO menuDTO){

        menuService.save(menuDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("deleteProduct/{id}")
    public void deleteProduct(@PathVariable ("id") int id){
        menuService.delete(id);
    }

    @PatchMapping("updateProduct/{id}")
    public void updateProductMenu(@RequestBody @Valid MenuDTO menuDTO, @PathVariable("id") int id){
        menuService.updateProduct(menuDTO,id);
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