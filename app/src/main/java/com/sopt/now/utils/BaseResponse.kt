package com.sopt.now.utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class BaseResponse {
    @SerialName("code")
    val code: String? = null
    @SerialName("message")
    val message: String? = null
}