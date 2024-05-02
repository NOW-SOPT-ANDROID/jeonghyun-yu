package com.sopt.now.model.info

import com.sopt.now.utils.BaseResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetInfoDto(
    @SerialName("authenticationId")
    val authenticationId: String,

    @SerialName("nickname")
    val nickname: String,

    @SerialName("phone")
    val phone: String
)