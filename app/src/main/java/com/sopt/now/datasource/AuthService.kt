package com.sopt.now.datasource

import com.sopt.now.model.login.RequestLoginDto
import com.sopt.now.model.signup.RequestSignUpDto
import com.sopt.now.config.BaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun signUp(
        @Body request: RequestSignUpDto,
    ): Response<BaseResponse<Unit>>

    @POST("member/login")
    suspend fun login(
        @Body request: RequestLoginDto
    ): Response<BaseResponse<Unit>>
}