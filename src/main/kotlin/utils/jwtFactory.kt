package com.r4men.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.r4men.entities.User
import java.util.*

@Suppress("INTEGER_OVERFLOW")
fun jwtFactory(user: User): String{
    val jwtIssuer = "easyEdu.com"
    val jwtSecret = "pomelo123"
    val token = JWT.create()
        .withIssuer(jwtIssuer)
        .withAudience(user.login)
        .withClaim("login", user.login)
        .withExpiresAt(Date(if(!user.rememberMe)System.currentTimeMillis() + 60000 * 180 else System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 30) )) // Токен будет действителен 1 минуту
        .sign(Algorithm.HMAC256(jwtSecret))
    return token
}