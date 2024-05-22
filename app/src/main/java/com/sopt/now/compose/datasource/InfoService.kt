package com.sopt.now.compose.datasource

import com.sopt.now.compose.model.userinfo.UserInfo
import com.sopt.now.compose.utils.BaseResponse
import retrofit2.Response
import retrofit2.http.GET

interface InfoService {
    @GET("member/info")
    suspend fun getUserInfo(): Response<BaseResponse<UserInfo>>
}