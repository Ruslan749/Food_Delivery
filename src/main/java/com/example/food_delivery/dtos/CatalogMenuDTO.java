package com.example.food_delivery.dtos;


import com.example.food_delivery.entities.Categories;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class CatalogMenuDTO {
    List<MenuDTO> MenuList;
    List<Categories> categoriesList;

    public CatalogMenuDTO(List<MenuDTO> menuList, List<Categories> categoriesList) {
        this.MenuList = menuList;
        this.categoriesList = categoriesList;
    }


}
