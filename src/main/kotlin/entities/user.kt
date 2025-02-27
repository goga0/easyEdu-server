package com.r4men.entities

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val login: String,
    val password: String,
    val rememberMe: Boolean = false
)

@Serializable
data class LoginResponse(
    val token: String
)