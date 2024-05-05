package com.sopt.now.presentation.auth.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.model.login.RequestLoginDto
import com.sopt.now.model.login.ResponseLoginDto
import com.sopt.now.utils.ApiFactory
import com.sopt.now.utils.ErrorResponse
import com.sopt.now.utils.NetworkUtil
import com.sopt.now.utils.ServicePool.loginService
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private var _status = MutableLiveData<Boolean>()
    var status: MutableLiveData<Boolean> = _status

    private var memberId: String? = null
    fun getMemberId() = memberId

    private var errorMessage: String? = null
    fun getErrorMessage() = errorMessage

    fun postLogin(data: RequestLoginDto) {
        viewModelScope.launch {
            runCatching {
                loginService.login(data)
            }.onSuccess {
                if (it.isSuccessful) {
                    _status.postValue(true)
                    memberId = it.headers()["location"]
                } else {
                    _status.postValue(false)
                    val errorBody = NetworkUtil.getErrorResponse(it.errorBody()!!)
                    errorMessage = errorBody?.message
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}