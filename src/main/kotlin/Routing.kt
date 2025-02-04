package com.r4men

import io.ktor.server.application.*
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
    }
}
