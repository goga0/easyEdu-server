package com.r4men.repository.interfaces

interface CrudRepository<T> {
    suspend fun create(entity: T?): Int
    suspend fun read(id: Int): T?
    suspend fun update(id: Int, entity: T): Boolean
    suspend fun delete(id: Int): Boolean
    suspend fun readAll(): List<T>
}