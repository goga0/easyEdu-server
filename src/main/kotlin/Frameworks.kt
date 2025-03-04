package com.r4men

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.slf4j.LoggerFactory

fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()
        modules(module {

        })
    }
    install(StatusPages) {
        // Обработка исключений
        exception<Throwable> { call, cause ->
            val logger = LoggerFactory.getLogger("Application")
            logger.error("An error occurred: ${cause.localizedMessage}", cause)
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError )
        }

        // Обработка 404 Not Found
        status(HttpStatusCode.NotFound) {call, status ->
            call.respondText("Page not found", status = status)
        }
    }
}
