package com.r4men

import com.r4men.entities.Users
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
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


}