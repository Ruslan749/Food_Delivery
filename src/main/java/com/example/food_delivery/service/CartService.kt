package com.example.food_delivery.service

//import com.example.food_delivery.entities.Cart
import com.example.food_delivery.entities.CartProduct
//import com.example.food_delivery.repositories.CartRepository
import com.example.food_delivery.repositories.MenuRepository
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class CartService {
    @Autowired
    private lateinit var requestInterceptService: RequestInterceptService

//    @Autowired
//    private lateinit var cartRepository: CartRepository

    @Autowired
    private lateinit var menuRepository: MenuRepository

//    fun getCart(token: String): Cart {
//        val userId = requestInterceptService.getUserId(token)
//        val cart = cartRepository.getCartByUserId(userId)
//
//        return if (cart.firstOrNull() != null) {
//            cart.first()
//        } else {
//            cartRepository.save(Cart(userId = userId))
//        }
//    }
//
//    fun addProduct(productId: Long, token: String): Cart {
//        val cart = cartRepository.getCartByUserId(requestInterceptService.getUserId(token))
//        val product = menuRepository.findById(productId)
//
//        CartProduct()
//
//        return if (cart.firstOrNull() != null) {
//            cart.first()
//        } else {
//            Cart()
//        }
//    }
//
//    fun changeQuantity(productId: Long, quantity: Long, token: String): Cart {
//        val cart = cartRepository.getCartByUserId(requestInterceptService.getUserId(token))
//        val productId = menuRepository.findById(productId)
//
//        return if (cart.firstOrNull() != null) {
//            cart.first()
//        } else {
//            Cart()
//        }
//    }
//
//    fun clearCart(token: String): Cart {
//        val cart = cartRepository.getCartByUserId(requestInterceptService.getUserId(token))
//
//        return if (cart.firstOrNull() != null) {
//            cart.first()
//        } else {
//            Cart()
//        }
//    }
//
//    fun removeProduct(productId: Long, token: String): Cart {
//        val cart = cartRepository.getCartByUserId(requestInterceptService.getUserId(token))
//
//        return if (cart.firstOrNull() != null) {
//            cart.first()
//        } else {
//            Cart()
//        }
//    }
}