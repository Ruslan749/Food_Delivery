package com.example.food_delivery.dtos

import com.example.food_delivery.entities.Menu
import lombok.Data
import org.springframework.stereotype.Component

@Data
@Component
data class CartDTO(
    val id: Long?,
    val productId: String?,
    val products: List<Menu>,
    val totalPrice: Double
) {
    constructor() : this(null, null, emptyList(), 0.0)
}