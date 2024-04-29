package com.sopt.now.model

import androidx.annotation.DrawableRes

sealed class Profile {
    data class MyProfile(
        val username: String,
        @DrawableRes val img: Int,
        val description: String
    ): Profile()

    data class FriendProfile(
        val username: String,
        @DrawableRes val img: Int
    ): Profile()

    data class FriendProfileWithMusic(
        val username: String,
        @DrawableRes val img: Int,
        val music: String
    ): Profile()
}
