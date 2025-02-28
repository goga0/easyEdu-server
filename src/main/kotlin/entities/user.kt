package com.r4men.entities

import kotlinx.serialization.Serializable

@Serializable
data class LoggingUser(
    val login: String,
    val password: String,
    val rememberMe: Boolean = false
)

@Serializable
data class RegisteringUser(
    val eduPlaceId: Int,
    val login: String,
    val password: String,
    val name: String,
    val surname: String,
    val role: Role
)

@Serializable
data class LoginResponse(
    val token: String,
    val role: Role
)

@Serializable
data class RegisterResponse(
    val registerStatus: Result
)

@Serializable
enum class Role {
    STUDENT, TEACHER
}

@Serializable
sealed class Result{
    data object Success : Result()
    data class Failure(val errorMessage: String): Result()
}