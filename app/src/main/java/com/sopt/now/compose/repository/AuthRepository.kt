package com.sopt.now.compose.repository

import com.sopt.now.compose.model.login.RequestLoginDto
import com.sopt.now.compose.model.signup.RequestSignUpDto
import com.sopt.now.compose.utils.BaseResponse
import retrofit2.Response

interface AuthRepository {
    suspend fun loginUser(data: RequestLoginDto): Response<BaseResponse<Unit>>

    suspend fun signUp(data: RequestSignUpDto): Response<BaseResponse<Unit>>
}