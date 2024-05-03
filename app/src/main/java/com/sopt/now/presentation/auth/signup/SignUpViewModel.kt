package com.sopt.now.presentation.auth.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.model.signup.RequestSignUpDto
import com.sopt.now.utils.ServicePool.authService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel: ViewModel() {
    private var _status = MutableLiveData<Boolean>()
    var status : MutableLiveData<Boolean> = _status

    fun signUp(data: RequestSignUpDto) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                authService.signUp(data)
            }.onSuccess {
                _status.postValue(true)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}