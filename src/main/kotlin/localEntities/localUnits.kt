package com.r4men.entities

import com.r4men.models.User
import kotlinx.serialization.Serializable

@Serializable
data class LoggingUser(
    val login: String,
    val password: String,
    val rememberMe: Boolean = false
)

@Serializable
data class UserUnit(
    val eduPlaceId: Int,
    val group: Int?,
    val login: String,
    val password: String?,
    val name: String,
    val surname: String,
    val role: String
)

@Serializable
data class StudentUnit(
    val eduPlaceId: Int,
    val group: Int?,
    val login: String,
    val name: String,
    val surname: String
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
enum class Role(val strValue: String) {
    STUDENT("STUDENT"),
    TEACHER("TEACHER")
}

@Serializable
data class GroupUnit(
    val title: String,
    val specialName: String
)

@Serializable
sealed class Result{
    data class Success(val successMessage: String) : Result()
    data class Failure(val errorMessage: String): Result()
}