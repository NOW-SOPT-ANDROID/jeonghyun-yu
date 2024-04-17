package com.sopt.now.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class SignUpData(
    val id: String,
    val password: String,
    val nickname: String,
    val mbti: String
) : Parcelable
