package com.sopt.now.compose.data

import com.sopt.now.compose.datasource.AuthService
import com.sopt.now.compose.model.login.RequestLoginDto
import com.sopt.now.compose.model.signup.RequestSignUpDto
import com.sopt.now.compose.repository.AuthRepository
import com.sopt.now.compose.utils.BaseResponse
import retrofit2.Response

class AuthRepositoryImpl(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun loginUser(data: RequestLoginDto): Response<BaseResponse<Unit>> {
        return authService.login(data)
    }

    override suspend fun signUp(data: RequestSignUpDto): Response<BaseResponse<Unit>> {
        return authService.signUp(data)
    }
}