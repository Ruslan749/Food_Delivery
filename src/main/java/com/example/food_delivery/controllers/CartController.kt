package com.example.food_delivery.controllers

//import com.example.food_delivery.entities.Cart
import com.example.food_delivery.service.CartService
import com.example.food_delivery.service.UserService
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
class CartController {
    @Autowired
    private lateinit var cartService: CartService

//    @GetMapping("/getCart")
//    fun getCart(@RequestHeader("Authorization") token: String): Cart = cartService.getCart(token)
//
//    @PostMapping("/addProduct")
//    fun addProduct(@RequestHeader("Authorization") token: String, @RequestParam productId: Long): Cart = cartService.addProduct(productId, token)
//
//    @PostMapping("/removeProduct")
//    fun removeProduct(@RequestHeader("Authorization") token: String, @RequestParam productId: Long): Cart = cartService.removeProduct(productId, token)
//
//    @PostMapping("changeQuantity")
//    fun changeQuantity(@RequestHeader("Authorization") token: String, @RequestParam productId: Long, @RequestParam quantity: Long): Cart {
//        return cartService.changeQuantity(productId, quantity, token)
//    }
//
//    @GetMapping("clearCart")
//    fun clearCart(@RequestHeader("Authorization") token: String, @RequestParam productId: Long): Cart = cartService.clearCart(token)


}