package com.example.food_delivery.service;


import com.example.food_delivery.dtos.CatalogMenuDTO;
import com.example.food_delivery.dtos.MenuDTO;
import com.example.food_delivery.entities.Categories;
import com.example.food_delivery.entities.Menu;
import com.example.food_delivery.repositories.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.food_delivery.repositories.CategoriesRepository;
import com.example.food_delivery.utils.MenuNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class menuService {

    private final MenuRepository menuRepository;
    private final CategoriesRepository categoriesRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public menuService(MenuRepository menuRepository, CategoriesRepository categoriesRepository, ModelMapper modelMapper) {
        this.menuRepository = menuRepository;
        this.categoriesRepository = categoriesRepository;
        this.modelMapper = modelMapper;
    }

    public CatalogMenuDTO findAll() {
        var categories = categoriesRepository.findAll();
        var menu = menuRepository.findAll().stream().map(this::convertToMenuDTO).toList();

        return new CatalogMenuDTO(menu,categories);
    }
    //перекладываем данные из Menu в MenuDTO
    private MenuDTO convertToMenuDTO(Menu menu) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(menu.getId());
        menuDTO.setDescription(menu.getDescription());
        menuDTO.setPrice(menu.getPrice());
        menuDTO.setNameFood(menu.getNameFood());
        menuDTO.setCategories_id(menu.getCategories().getId());
        return menuDTO;
    }

    private Menu convertToMenu(MenuDTO menuDTO) {
        Menu menu = new Menu();
        Optional<Categories> categories =categoriesRepository.findById(menuDTO.getCategories_id());
        menu.setNameFood(menuDTO.getNameFood());
        menu.setPrice(menuDTO.getPrice());
        menu.setDescription(menuDTO.getDescription());
        menu.setCategories(categories.get());

        return menu;
    }

    public Menu findOne(Long id) {
        Optional<Menu> foundMenu = menuRepository.findById(id);
        return foundMenu.orElseThrow(MenuNotFoundException::new);
    }

    @Transactional
    public Menu save (MenuDTO menuDTO){
        return menuRepository.save(convertToMenu(menuDTO));
    }
    @Transactional
    public void delete(Long id){
        menuRepository.deleteById(id);
    }

    @Transactional
    public void updateProduct(MenuDTO menuDTO, Long id) {
        Menu menu = convertToMenu(menuDTO);
        menu.setId(id);
        menuRepository.save(menu);
    }
}
