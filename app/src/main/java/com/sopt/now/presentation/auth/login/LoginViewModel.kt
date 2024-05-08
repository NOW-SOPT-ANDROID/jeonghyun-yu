package com.sopt.now.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.model.login.RequestLoginDto
import com.sopt.now.utils.NetworkUtil
import com.sopt.now.utils.ServicePool.loginService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> get() = _status

    private var _memberId: String? = null
    val memberId: String? get() = _memberId

    private var _errorMessage: String? = null
    val errorMessage: String? get() = _errorMessage

    fun postLogin(data: RequestLoginDto) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                loginService.login(data)
            }.onSuccess {
                if (it.isSuccessful) {
                    _memberId = it.headers()["location"]
                    _status.postValue(true)
                } else {
                    _errorMessage = it.errorBody()
                        ?.let { e -> NetworkUtil.getErrorResponse(e)?.message }
                    _status.postValue(false)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}