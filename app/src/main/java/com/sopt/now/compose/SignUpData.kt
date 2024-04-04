package com.sopt.now.compose

import java.io.Serializable

data class SignUpData(
    val id: String,
    val password: String,
    val nickname: String,
    val mbti: String
) : Serializable
