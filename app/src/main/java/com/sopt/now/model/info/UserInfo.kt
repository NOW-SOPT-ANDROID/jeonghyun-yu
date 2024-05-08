package com.sopt.now.model.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    @SerialName("authenticationId")
    val authenticationId: String,

    @SerialName("nickname")
    val nickname: String,

    @SerialName("phone")
    val phone: String
)