package com.sopt.now.datasource

import com.sopt.now.model.info.UserInfo
import com.sopt.now.utils.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface InfoService {
    @GET("member/info")
    suspend fun getUserInfo(
        @Header("memberId") memberId: String
    ): Response<BaseResponse<UserInfo>>
}