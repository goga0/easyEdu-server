package com.r4men

import com.r4men.dbUtils.DatabaseFactory
import com.r4men.entities.*
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.SchemaUtils

fun Application.configureDatabase(){
    DatabaseFactory.init()

    SchemaUtils.create( EduPlace ,Users, Students, Teachers, Subjects, Groups, Lessons, Marks, Themes)
}