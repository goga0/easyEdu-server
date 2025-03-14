package com.r4men.routes

import com.r4men.entities.UserUnit
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Route.authRoutes(){
    route("auth"){

        post {
            val request = call.receive<UserUnit>()
        }

    }
}