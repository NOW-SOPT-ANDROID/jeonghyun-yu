package com.sopt.now.datasource

import com.sopt.now.model.info.UserInfo
import com.sopt.now.utils.BaseResponse
import retrofit2.Response
import retrofit2.http.GET

interface InfoService {
    @GET("member/info")
    suspend fun getUserInfo(): Response<BaseResponse<UserInfo>>
}