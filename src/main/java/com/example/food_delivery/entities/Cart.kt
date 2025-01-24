//package com.example.food_delivery.entities
//
//import javax.persistence.*
//
//
//@Entity
//@Table(name = "cart")
//data class Cart(
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val id: Long? = null,
//
//    @Column(name = "user_id", nullable = false)
//    val userId: Long,
//
////    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
////    @JoinColumn(name = "cart_id")
////    val products: List<Menu> = emptyList(),
//
//    @OneToMany(mappedBy = "cart", cascade = [CascadeType.ALL], orphanRemoval = true)
//    val cartProducts: List<CartProduct> = mutableListOf(),
//
//    val totalPrice: Double = 0.0,
//) {
//    // Публичный или защищённый конструктор без аргументов
//    constructor() : this(null, 0, emptyList(), 0.0)
//
////    fun addProduct(cartProduct: CartProduct): Cart {
////        val products = this.cartProducts.toMutableList()
////        products.add(cartProduct)
////
////        return this.copy(cartProducts = products)
////    }
////
////    fun addProduct(cartProduct: CartProduct): Cart {
////        val products = this.cartProducts.toMutableList()
////        products.add(cartProduct)
////
////        return this.copy(cartProducts = products)
////    }
//}