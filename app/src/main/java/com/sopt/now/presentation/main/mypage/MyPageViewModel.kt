package com.sopt.now.presentation.main.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.model.info.UserInfo
import com.sopt.now.utils.NetworkUtil
import com.sopt.now.utils.ServicePool.infoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {
    private lateinit var userInfo : UserInfo
    fun getUserInfo() = userInfo

    private var _status = MutableLiveData<Boolean>()
    var status: MutableLiveData<Boolean> = _status

    private var errorMessage: String? = null
    fun getErrorMessage() = errorMessage

    fun getUserInfo(memberId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                infoService.getUserInfo(memberId)
            }.onSuccess {
                if (it.isSuccessful) {
                    _status.postValue(true)
                    userInfo = it.body()?.data!!
                } else {
                    _status.postValue(false)
                    errorMessage = NetworkUtil.getErrorResponse(it.errorBody()!!)?.message
                }
            }.onFailure {
                it.printStackTrace()
            }

        }
    }

}