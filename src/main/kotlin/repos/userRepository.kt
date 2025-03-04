package com.r4men.repos

import com.r4men.entities.Role
import com.r4men.entities.UserUnit
import com.r4men.models.Users
import com.r4men.utils.ShaHasher
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseTransactions {

    fun createUser(user: UserUnit): Int?{
        return try {
            transaction {
                val userId = Users.insertAndGetId {
                    it[eduPlaceId] = user.eduPlaceId
                    it[login] = user.login
                    it[name] = user.name
                    it[surname] = user.surname
                    it[password] = ShaHasher.hashPassword(user.password)
                    it[role] = user.role
                }
                userId.value
            }
        } catch(e: Exception){
            println(e)
            null
        }
    }

    fun findUserByID(){

    }

    fun updateExistingUser(){

    }

    fun deleteUser(){

    }

}