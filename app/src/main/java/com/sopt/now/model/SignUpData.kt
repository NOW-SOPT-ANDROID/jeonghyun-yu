package com.sopt.now.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpData(
    val id: String,
    val password: String,
    val nickname: String,
    val mbti: String
) : Parcelable
