package com.r4men

import com.r4men.entities.*
import com.r4men.models.Users
import com.r4men.repos.UserDatabaseTransactions
import com.r4men.utils.jwtFactory
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction


fun Application.configureRouting() {

    // Настройка JWT

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }

    routing {
        post("/register"){
            val regCredentials = call.receive<UserUnit>()
            val registeredUserId = if( regCredentials.role == Role.STUDENT.strValue){
                UserDatabaseTransactions.createStudent(regCredentials)
            }  else if(regCredentials.role == Role.TEACHER.strValue){
                UserDatabaseTransactions.createTeacher(regCredentials)
        } else {
            null
            }
            }
            if(registeredUserId != null){
                call.respond(Result.Success("User added Successfully"))
            } else {
                call.respond(Result.Failure("something went wrong ${StatusPages.key}"))
            }
        }


        post("/login"){
            val user = call.receive<UserUnit>()
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

