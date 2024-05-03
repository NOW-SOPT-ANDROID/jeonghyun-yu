package com.sopt.now.compose.model.userinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class ResponseGetInfoDto(
    @SerialName("code")
    val code: Int,

    @SerialName("message")
    val message: String,

    @SerialName("data")
    val data: UserInfo
)

@Serializable
data class UserInfo(
    @SerialName("authenticationId")
    val authenticationId: String,

    @SerialName("nickname")
    val nickname: String,

    @SerialName("phone")
    val phone: String
)
