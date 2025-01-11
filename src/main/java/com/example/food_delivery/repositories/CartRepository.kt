package com.example.food_delivery.repositories

import com.example.food_delivery.entities.Cart
import com.example.food_delivery.entities.Categories
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository: JpaRepository<Cart, Int> {
    fun getCartById(id: Long): MutableList<Cart>
}