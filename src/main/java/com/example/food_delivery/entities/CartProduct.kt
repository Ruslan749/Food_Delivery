package com.example.food_delivery.entities

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "cart_product")
data class CartProduct(
    @EmbeddedId
    val id: CartProductId,

//    @MapsId("cartId")
//    @JoinColumn(name = "cart_id", nullable = false)
//    val cartId: Long,

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    val product: Menu
) { constructor() : this(CartProductId(), Menu()) }

@Embeddable
data class CartProductId(
    val cartId: Long,
    val productId: Long
) : Serializable {
    constructor() : this(0, 0)
}