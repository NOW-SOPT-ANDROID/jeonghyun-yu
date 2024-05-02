package com.sopt.now.model.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignUpDto(
    @SerialName("status")
    val code: Int,

    @SerialName("message")
    val message: String,
)