package com.sopt.now.compose.presentation.auth.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.model.signup.RequestSignUpDto
import com.sopt.now.compose.utils.ServicePool.authService
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