package com.sopt.now.presentation.main.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.model.info.ResponseGetInfoDto
import com.sopt.now.model.info.UserInfo
import com.sopt.now.utils.ServicePool.infoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {
    private var _userInfo = MutableLiveData<UserInfo>()
    var userInfo : MutableLiveData<UserInfo> = _userInfo

    fun getUserInfo(memberId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                infoService.getUserInfo(memberId)
            }.onSuccess {
                _userInfo.postValue(it.body()?.data)
            }.onFailure {
                it.printStackTrace()
            }

        }
    }

}