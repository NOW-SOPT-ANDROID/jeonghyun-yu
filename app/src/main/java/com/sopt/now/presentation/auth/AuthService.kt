package com.sopt.now.presentation.auth

import com.sopt.now.model.login.RequestLoginDto
import com.sopt.now.model.login.ResponseLoginDto
import com.sopt.now.model.signup.RequestSignUpDto
import com.sopt.now.model.signup.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>

    @POST("member/login")
    fun login(
        @Body request: RequestLoginDto
    ): Call<ResponseLoginDto>
}