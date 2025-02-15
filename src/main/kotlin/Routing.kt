package com.r4men

import com.r4men.entities.User
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureRouting() {
    routing {

        get("/"){
            call.respondRedirect("/main")
        }

        get("/main") {
            val file = File("src/main/resources/static/index.html")
            call.respondFile(file)
        }

        post("/login"){
            call.receive<User>()
        }

    }
}
