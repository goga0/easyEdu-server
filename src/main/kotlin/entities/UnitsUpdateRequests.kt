package com.r4men.entities

import kotlinx.serialization.Serializable

@Serializable
data class UserUpdateReq(
    val eduPlaceId: Int,
    val group: Int?,
    val login: String,
    val password: String,
    val name: String,
    val surname: String,
    val role: String
)

@Serializable
data class StudentUpdateReq(
    val eduPlaceId: Int,
    val group: Int?,
    val login: String,
    val name: String,
    val surname: String
)

@Serializable
data class GroupUpdateReq(
    val title: String,
    val specialName: String
)