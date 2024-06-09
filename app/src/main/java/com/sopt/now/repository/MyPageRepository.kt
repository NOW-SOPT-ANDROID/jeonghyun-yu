package com.sopt.now.repository

import com.sopt.now.model.info.UserInfo
import com.sopt.now.config.BaseResponse
import retrofit2.Response

interface MyPageRepository {
    suspend fun getUserInfo(): Response<BaseResponse<UserInfo>>
}