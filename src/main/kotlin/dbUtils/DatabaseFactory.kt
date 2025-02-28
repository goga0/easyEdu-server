package com.r4men.dbUtils

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig
import org.jetbrains.exposed.sql.transactions.TransactionManager
import java.sql.Connection

object DatabaseFactory {
    fun init(){
        Database.connect(
            url = "jdbc:postgresql://localhost:5432/postgres",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "Axiomazzz_1903",
            databaseConfig = DatabaseConfig{
                defaultMaxAttempts = 3
            }
        )
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
    }
}