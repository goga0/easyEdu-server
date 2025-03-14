package com.r4men

import com.r4men.dbUtils.DatabaseFactory
import com.r4men.models.*
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.SchemaUtils

fun Application.configureDatabase(){
    DatabaseFactory.init()

    SchemaUtils.create( EduPlaces , Users, Students, Teachers, Subjects, Groups, Lessons, Marks)
}