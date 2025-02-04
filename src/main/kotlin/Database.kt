package com.r4men

import com.r4men.entities.UserType
import com.r4men.entities.Users
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabase(){
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/postgres",
        user = "postgres",
        password = "Axiomazzz_1903"
    )

    transaction {
        SchemaUtils.create(Users)
    }

    fun String.toUser(): UserType? {
        return UserType.fromString(this)
    }

    fun insertUser(
        name: String,
        surname: String,
        userType: UserType
        ){
        transaction {
            Users.insert {
                it[Users.name] = name
                it[Users.surname] = surname
                it[Users.UserType] = userType.type
            }
        }
    }
    fun getUsers():  List<Pair<Int, Pair<Pair<String, String>, UserType>>>{
        return transaction {
            Users.selectAll().map(){
                it[Users.id].value to (it[Users.name] to it[Users.surname] to UserType.fromString(it[Users.UserType])!!)
            }
        }
    }
}