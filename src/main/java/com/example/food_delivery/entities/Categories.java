package com.example.food_delivery.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Categories {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "categoriesName")
    private String categoriesName;

    @JsonIgnore
    @OneToMany(mappedBy = "categories",cascade = CascadeType.ALL)
    private List<Menu> menuList;


}
