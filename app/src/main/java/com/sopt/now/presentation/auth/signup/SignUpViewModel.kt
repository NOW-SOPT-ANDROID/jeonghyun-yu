package com.sopt.now.presentation.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.model.signup.RequestSignUpDto
import com.sopt.now.utils.NetworkUtil
import com.sopt.now.utils.ServicePool.authService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> get() = _status

    private var _errorMessage: String? = null
    val errorMessage: String? get() = _errorMessage

    fun signUp(data: RequestSignUpDto) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                authService.signUp(data)
            }.onSuccess {
                if (it.isSuccessful) _status.postValue(true)
                else {
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