package com.sopt.now.presentation.main.mypage

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

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> get() = _status


    private var _errorMessage: String? = null
    val errorMessage: String? get() = _errorMessage

    fun getUserInfo(memberId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                infoService.getUserInfo()
            }.onSuccess {
                if (it.isSuccessful) {
                    _status.postValue(true)
                    it.body()?.data.also { info ->
                        if (info != null) {
                            _userInfo = info
                        }
                    }
                } else {
                    _status.postValue(false)
                    _errorMessage = it.errorBody()
                        ?.let { e -> NetworkUtil.getErrorResponse(e)?.message }
                }
            }.onFailure {
                it.printStackTrace()
            }

        }
    }

}