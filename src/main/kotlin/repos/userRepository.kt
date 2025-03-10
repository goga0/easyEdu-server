package com.r4men.repos

import com.r4men.dbUtils.DbError
import com.r4men.entities.GroupUnit
import com.r4men.entities.StudentUnit
import com.r4men.entities.UserUnit
import com.r4men.models.Group
import com.r4men.models.Groups
import com.r4men.models.Students
import com.r4men.models.Users
import com.r4men.utils.ShaHasher
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction

object UserDatabaseTransactions {

    suspend fun createStudent(user: UserUnit): Int?{
        return try {
            val userId = transaction {
                Users.insertAndGetId {
                    it[eduPlaceId] = user.eduPlaceId
                    it[login] = user.login
                    it[name] = user.name
                    it[surname] = user.surname
                    it[password] = ShaHasher.hashPassword(user.password!!)
                    it[role] = user.role
                }.value
            }
            transaction {
                Students.insert {
                    it[Students.userId] = userId
                    it[name] = user.name
                    it[login] = user.login
                    it[groupId] = user.group!!
                }
            }
            userId
        } catch(e: Exception){
            println(e)
            null
        }
    }

    suspend fun findStudentByID(id: Int): StudentUnit?{
        return try{
            transaction {
                Users.select(Users.id).where{
                    Users.id eq id
                }.map{
                    StudentUnit(
                        eduPlaceId = it[Students.eduPlaceId].value,
                        group = it[Students.groupId].value,
                        login = it[Students.login],
                        name = it[Students.name],
                        surname = it[Students.surname],
                    )
                }.singleOrNull()
            }
        } catch(e: Exception){
            println(e)
            null
        }
    }

    fun updateExistingUser(user: UserUnit){
        transaction {

        }
    }

    fun deleteUser(){

    }

}

object GroupDatabaseTransactions{

    suspend fun createGroup(group: GroupUnit): Int?{
        return try{
            transaction {
                Groups.insertAndGetId {
                    it[title] = title
                    it[specialName] = specialName
                }.value
            }
        } catch(e: Exception){
            println(e)
            null
        }
    }

    suspend fun findGroupByID(id: Int): GroupUnit?{
        return try {
            transaction {
                Groups.select(Groups.id).where{
                    Groups.id eq id
                }.map{
                    GroupUnit(
                        title = it[Groups.title],
                        specialName = it[Groups.specialName]
                    )
                }.singleOrNull()
           }
        } catch (e: Exception){
            println(e)
            null
        }
    }
}