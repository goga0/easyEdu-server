package com.r4men.dbUtils


data class DbError(
    val code: String? = null,
    override val message: String? = null,
    val details: String? = null,
    override val cause: Throwable? = null
): Error(message, cause)