package com.sopt.now.data

import com.sopt.now.datasource.AuthService
import com.sopt.now.model.login.RequestLoginDto
import com.sopt.now.model.signup.RequestSignUpDto
import com.sopt.now.repository.AuthRepository
import com.sopt.now.utils.BaseResponse
import retrofit2.Response

class AuthRepositoryImpl(
    private val authService: AuthService
): AuthRepository {
    override suspend fun loginUser(data: RequestLoginDto): Response<BaseResponse<Unit>> {
        return authService.login(data)
    }
}