package com.example.food_delivery.repositories;


import com.example.food_delivery.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Integer> {


}
