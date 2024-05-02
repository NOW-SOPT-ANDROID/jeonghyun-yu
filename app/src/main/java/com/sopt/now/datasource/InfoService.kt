package com.sopt.now.datasource

import com.sopt.now.model.info.ResponseGetInfoDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface InfoService {
    @GET("member/info")
    fun getUserInfo(
        @Header("memberId") memberId: String
    ): Call<ResponseGetInfoDto>
}