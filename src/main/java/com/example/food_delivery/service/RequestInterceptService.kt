package com.example.food_delivery.service

import com.example.food_delivery.utils.JwtTokenUtils
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class RequestInterceptService {
    @Autowired
    private lateinit var jwtTokenUtils: JwtTokenUtils

    fun getUserId(token: String): Long = jwtTokenUtils.getUserId(token)
}