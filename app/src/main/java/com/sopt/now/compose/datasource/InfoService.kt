package com.sopt.now.compose.datasource

import com.sopt.now.compose.model.userinfo.ResponseGetInfoDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface InfoService {
    @GET("member/info")
    fun getUserInfo(
        @Header("memberId") memberId: String
    ): Call<ResponseGetInfoDto>
}