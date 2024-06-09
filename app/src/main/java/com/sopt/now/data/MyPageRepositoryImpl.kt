package com.sopt.now.data

import com.sopt.now.datasource.InfoService
import com.sopt.now.model.info.UserInfo
import com.sopt.now.repository.MyPageRepository
import com.sopt.now.config.BaseResponse
import retrofit2.Response

class MyPageRepositoryImpl(
    private val infoService: InfoService
) : MyPageRepository {
    override suspend fun getUserInfo(): Response<BaseResponse<UserInfo>> {
        return infoService.getUserInfo()
    }

}