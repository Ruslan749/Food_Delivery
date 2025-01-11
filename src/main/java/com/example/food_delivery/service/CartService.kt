package com.example.food_delivery.service

import com.example.food_delivery.entities.Cart
import com.example.food_delivery.exceptions.AppError
import com.example.food_delivery.repositories.CartRepository
import com.example.food_delivery.utils.JwtTokenUtils
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestHeader
import kotlin.jvm.Throws

@Service
@RequiredArgsConstructor
class CartService {

    private val userService: UserService? = null

    @Autowired
    private lateinit var jwtTokenUtils: JwtTokenUtils

    @Autowired
    private lateinit var cartRepository: CartRepository

    fun getCart(token: String): Cart {
        val userId = jwtTokenUtils.getUserId(token)
        val cart = cartRepository.getCartById(userId)

        return if (cart.firstOrNull() != null) {
            cart.first()
        } else {
            Cart()
        }
    }
}