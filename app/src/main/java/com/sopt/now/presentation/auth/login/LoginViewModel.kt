package com.sopt.now.presentation.auth.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.model.login.RequestLoginDto
import com.sopt.now.model.login.ResponseLoginDto
import com.sopt.now.utils.ServicePool.loginService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private var _login = MutableLiveData<ResponseLoginDto>()
    var login : MutableLiveData<ResponseLoginDto> = _login

    private var memberId: String ?= null
    fun getMemberId() = memberId

    fun postLogin(data: RequestLoginDto) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                loginService.login(data)
            }.onSuccess {
                _login.postValue(it.body())
                memberId = it.headers()["location"]
            }.onFailure {
         
                it.printStackTrace()
            }
        }
    }
}