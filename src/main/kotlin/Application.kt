package com.r4men

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureFrameworks()
    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()
    configureDatabase()
    install(CallLogging) {
        level = Level.INFO
        format { call -> "${call.request.httpMethod.value} ${call.request.uri}" }
    }
}
