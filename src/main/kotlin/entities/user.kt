package com.r4men.entities

import org.jetbrains.exposed.dao.id.IntIdTable

object Users : IntIdTable() {
    val name = varchar("name", 50)
    val surname = varchar("surname", 50)
    val UserType = varchar("user_type", 10)
    val group: String = reference()
}

enum class UserType(val type: String){
    STUDENT("student"),
    TEACHER("teacher");

    companion object {
        fun fromString(type: String): UserType?{
            return entries.find{it.type.equals(type, ignoreCase = true)}
        }
    }
}