package com.sopt.now.compose.presentation.main.mypage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.model.userinfo.ResponseGetInfoDto
import com.sopt.now.compose.model.userinfo.UserInfo
import com.sopt.now.compose.utils.ServicePool.infoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageViewModel: ViewModel() {
    private var _userInfo = MutableLiveData<ResponseGetInfoDto>()
    var userInfo : MutableLiveData<ResponseGetInfoDto> = _userInfo

    fun getUserInfo(memberId: String) {
        infoService.getUserInfo(memberId).enqueue(object: Callback<ResponseGetInfoDto> {
            override fun onResponse(
                call: Call<ResponseGetInfoDto>,
                response: Response<ResponseGetInfoDto>
            ) {
                if (response.isSuccessful) {
                    userInfo.postValue(response.body())
                } else {
                    Log.d("error", response.message())
                }
            }

            override fun onFailure(call: Call<ResponseGetInfoDto>, t: Throwable) {
                Log.d("server error", t.message.toString())
            }

        })
    }
}