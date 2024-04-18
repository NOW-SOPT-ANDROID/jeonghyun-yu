package com.sopt.now.compose.model

import androidx.annotation.DrawableRes

sealed class Profile {
    data class MyProfile(
        val username: String,
        @DrawableRes val img: Int,
        val description: String
    ): Profile()

    data class FriedProfile(
        val username: String,
        @DrawableRes val img: Int
    ): Profile()
}