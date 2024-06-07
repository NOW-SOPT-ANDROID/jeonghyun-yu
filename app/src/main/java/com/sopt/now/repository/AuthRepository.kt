package com.sopt.now.repository

import com.sopt.now.model.login.RequestLoginDto
import com.sopt.now.model.signup.RequestSignUpDto
import com.sopt.now.utils.BaseResponse
import retrofit2.Response

interface AuthRepository {
    suspend fun loginUser(data: RequestLoginDto): Response<BaseResponse<Unit>>

    suspend fun signupUser(data: RequestSignUpDto): Response<BaseResponse<Unit>>
}