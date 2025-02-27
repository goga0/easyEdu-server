package com.r4men

import com.r4men.entities.LoginResponse
import com.r4men.entities.User
import com.r4men.entities.Users
import com.r4men.utils.jwtFactory
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureRouting() {

    // Настройка JWT


    routing {

        post("/login"){
            val user = call.receive<User>()
            val existingUser = transaction {
                Users.selectAll().where{ Users.login eq user.login }
                    .map{
                    User(it[Users.login], it[Users.password])
                }
                    .singleOrNull()
            }
            if (existingUser != null) {
                user.login
                // Здесь вы можете добавить проверку пароля
                if (existingUser.password == user.password) {
                    // Генерация JWT токена
                    val token = jwtFactory(user)
                    // Отправка ответа с токеном
                    call.respond(HttpStatusCode.OK, LoginResponse(token))
                } else {
                    call.respond(HttpStatusCode.Unauthorized, "Invalid password")
                }
            } else {
                call.respond(HttpStatusCode.NotFound, "User  not found")
            }
        }

        get("/refresh-token/${Users.id}"){
            val user = call.receive<User>()
            call.respond(jwtFactory(user))
        }
    }
}
