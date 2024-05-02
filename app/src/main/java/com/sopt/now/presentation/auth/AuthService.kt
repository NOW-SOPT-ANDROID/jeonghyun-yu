package com.sopt.now.presentation.auth

import com.sopt.now.model.login.ResponseGetInfoDto
import com.sopt.now.model.signup.RequestSignUpDto
import com.sopt.now.model.signup.ResponseSignUpDto
import com.sopt.now.utils.BaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>
}