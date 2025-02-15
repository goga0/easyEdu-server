package com.r4men.entities

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val login: String,
    val password: String
)