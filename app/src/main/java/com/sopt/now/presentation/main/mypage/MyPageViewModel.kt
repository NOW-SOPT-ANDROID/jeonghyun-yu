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
    private lateinit var _userInfo: UserInfo
    val userInfo: UserInfo get() = _userInfo
    //fun getUserInfo() = userInfo

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> get() = _status


    private var _errorMessage: String? = null
    val errorMessage: String? get() = _errorMessage

    fun getUserInfo(memberId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                infoService.getUserInfo(memberId)
            }.onSuccess {
                if (it.isSuccessful) {
                    _status.postValue(true)
                    _userInfo = it.body()?.data!!
                } else {
                    _status.postValue(false)
                    _errorMessage = NetworkUtil.getErrorResponse(it.errorBody()!!)?.message
                }
            }.onFailure {
                it.printStackTrace()
            }

        }
    }

}