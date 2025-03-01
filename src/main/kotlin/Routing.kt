package com.r4men

import com.r4men.entities.*
import com.r4men.utils.ShaHasher
import com.r4men.utils.jwtFactory
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction


fun Application.configureRouting() {

    // Настройка JWT



    routing {
        post("/register"){
            val regCredentials = call.receive<RegisteringStudent>()
            val registeredUserId = transaction {
                if (regCredentials.role == Role.STUDENT) {
                    Users.insertAndGetId {
                        it[eduPlaceId] = regCredentials.eduPlaceId
                        it[login] = regCredentials.login
                        it[name] = regCredentials.name
                        it[surname] = regCredentials.surname
                        it[password] = ShaHasher.hashPassword(regCredentials.password)
                    }
                }
            }
            val addingStudent = transaction{
                Students.insert {
                    it[group] = regCredentials.group
                }
            }

            }
        }


        post("/login"){
            val user = call.receive<LoggingUser>().body()
            val existingUser = transaction {
                Users.selectAll().where{ Users.login eq user.login }
                    .map{
                    user(it[Users.login], it[Users.password])
                }
                    .singleOrNull()
            }
            if (existingUser != null) {
                // Здесь вы можете добавить проверку пароля
                if (existingUser.password == user.password) {
                    // Генерация JWT токена
                    val token = jwtFactory(user)
                    // Отправка ответа с токеном
                    call.respond(HttpStatusCode.OK, LoginResponse(token, existingUser.))
                } else {
                    call.respond(HttpStatusCode.Unauthorized, "Invalid password")
                }
            } else {
                call.respond(HttpStatusCode.NotFound, "User  not found")
            }
        }

        get("/refresh-token/${Users.id}"){
            val user = call.receive<LoggingUser>()
            call.respond(jwtFactory(user))
        }

        get("/databaseApi/"){
            val request = call.receive<RegisteringSteudent>()
            if(request.token)
            val registeredUser = transaction {
                Users.insertAndGetId {

                }
            }
        }
    }
}
