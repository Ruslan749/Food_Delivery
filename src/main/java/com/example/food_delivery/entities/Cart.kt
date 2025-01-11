package com.example.food_delivery.entities

import javax.persistence.*


@Entity
@Table(name = "cart")
data class Cart(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    val products: List<Menu> = emptyList(),

    val totalPrice: Double = 0.0,
) {
    // Публичный или защищённый конструктор без аргументов
    constructor() : this(null, emptyList(), 0.0)
}

//
//@Entity
//@Table(name = "cart_items")
//data class CartItem(
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val id: Long? = null,
//
//    @ManyToOne
//    @JoinColumn(name = "cart_id")
//    val cart: Cart,
//
//    val productId: Long,
//
//    val quantity: Int = 1
//)