package com.r4men

import com.r4men.dbUtils.DatabaseFactory
import com.r4men.entities.*
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabase(){
    DatabaseFactory.init()

    SchemaUtils.create(Users, Students, Teachers, Subjects, Groups, Lessons, Marks, Themes)
}