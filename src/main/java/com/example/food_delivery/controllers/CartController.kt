package com.example.food_delivery.controllers

import com.example.food_delivery.entities.Cart
import com.example.food_delivery.service.CartService
import com.example.food_delivery.service.UserService
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
class CartController {
    @Autowired
    private lateinit var cartService: CartService

    @GetMapping("/getCart")
    fun getCart(): Cart = cartService.getCart("token")
}