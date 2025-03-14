package com.r4men.repository.implementations

import com.r4men.entities.StudentUnit
import com.r4men.entities.UserUnit
import com.r4men.models.Student
import com.r4men.models.User
import com.r4men.repository.interfaces.CrudRepository
import com.r4men.utils.ShaHasher
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction


class StudentRepository : CrudRepository<UserUnit> {
    override suspend fun create(entity: UserUnit? ): Int {
        return if(entity!!.group != null){
            newSuspendedTransaction {
                val userId = User.new{
                    this.login = login
                    this.name = name
                    this.surname = surname
                    this.password = ShaHasher.hashPassword(password)
                    this.role = role
                }
                Student.new {
                    this.studentId = userId
                }
            }
        } else {

        }
    }

    override suspend fun read(id: Int): UserUnit? {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: Int, entity: UserUnit): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun readAll(): List<UserUnit> {
        TODO("Not yet implemented")
    }
}