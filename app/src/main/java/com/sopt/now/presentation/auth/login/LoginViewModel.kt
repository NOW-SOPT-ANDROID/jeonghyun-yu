package com.sopt.now.presentation.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.model.login.RequestLoginDto
import com.sopt.now.utils.NetworkUtil
import com.sopt.now.utils.ServicePool.loginService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private var _status = MutableLiveData<Boolean>()
    var status: MutableLiveData<Boolean> = _status

    private var memberId: String? = null
    fun getMemberId() = memberId

    private var errorMessage: String? = null
    fun getErrorMessage() = errorMessage

    fun postLogin(data: RequestLoginDto) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                loginService.login(data)
            }.onSuccess {
                if (it.isSuccessful) {
                    memberId = it.headers()["location"]
                    _status.postValue(true)
                } else {
                    errorMessage = it.errorBody()
                        ?.let { e -> NetworkUtil.getErrorResponse(e)?.message }
                    _status.postValue(false)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}