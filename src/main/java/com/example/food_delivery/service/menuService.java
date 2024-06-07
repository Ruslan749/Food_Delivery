package com.example.food_delivery.service;


import com.example.food_delivery.dtos.CatalogMenuDTO;
import com.example.food_delivery.dtos.MenuDTO;
import com.example.food_delivery.entities.Menu;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.food_delivery.repositories.CategoriesRepository;
import com.example.food_delivery.utils.MenuNotFoundException;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class menuService {

    private final com.example.food_delivery.repositories.menuRepository menuRepository;
    private final CategoriesRepository categoriesRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public menuService(com.example.food_delivery.repositories.menuRepository menuRepository, CategoriesRepository categoriesRepository, ModelMapper modelMapper) {
        this.menuRepository = menuRepository;
        this.categoriesRepository = categoriesRepository;
        this.modelMapper = modelMapper;
    }

    public CatalogMenuDTO findAll() {
        var categories = categoriesRepository.findAll();
        var menu = menuRepository.findAll().stream().map(this::convertToMenuDTO).toList();

        return new CatalogMenuDTO(menu,categories);
    }

    private MenuDTO convertToMenuDTO(Menu menu) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(menu.getId());
        menuDTO.setDescription(menu.getDescription());
        menuDTO.setPrice(menu.getPrice());
        menuDTO.setNameFood(menu.getNameFood());
        menuDTO.setCategories_id(menu.getCategories().getId());
        return menuDTO;
    }

    //перекладываем данные из Menu в MenuDTO

    public Menu findOne(int id) {
        Optional<Menu> foundMenu = menuRepository.findById(id);
        return foundMenu.orElseThrow(MenuNotFoundException::new);
    }

    @Transactional
    public void save (Menu menu){
        menuRepository.save(menu);
    }
}
