package com.r4men.entities

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val surname: String,
    val userType: UserType,
    val group: String
)

enum class UserType(val type: String){
    STUDENT("student"),
    TEACHER("teacher");

    companion object {
        fun fromString(type: String): UserType?{
            return entries.find{it.type.equals(type, ignoreCase = true)}
        }
    }
}