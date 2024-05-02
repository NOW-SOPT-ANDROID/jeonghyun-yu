package com.sopt.now.utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

open class BaseResponse(
    @SerialName("code")
    val code: String,

    @SerialName("message")
    val message: String
)
