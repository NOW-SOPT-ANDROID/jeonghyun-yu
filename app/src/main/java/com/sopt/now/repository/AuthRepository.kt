package com.sopt.now.repository

import com.sopt.now.model.login.RequestLoginDto
import com.sopt.now.utils.BaseResponse
import retrofit2.Response

interface AuthRepository {
    suspend fun loginUser(data: RequestLoginDto): Response<BaseResponse<Unit>>
}