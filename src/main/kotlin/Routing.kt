package com.r4men

import com.r4men.routes.authRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, "Internal server error: ${cause.localizedMessage}")
        }
        exception<NoSuchElementException>{ call, cause ->
            call.respond(HttpStatusCode.NotFound,"Not found ${cause.message}")
        }
    }

    routing {
        route("api"){
            authRoutes()
        }
    }

}
