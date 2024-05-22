package com.sopt.now.presentation.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.model.signup.RequestSignUpDto
import com.sopt.now.utils.NetworkUtil
import com.sopt.now.utils.ServicePool.authService
import com.sopt.now.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val _state = MutableStateFlow<UiState>(UiState.LOADING)
    val state: StateFlow<UiState> = _state

    fun signUp(data: RequestSignUpDto) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = UiState.LOADING
            runCatching {
                authService.signUp(data)
            }.onSuccess {
                if (it.isSuccessful) _state.value = UiState.SUCCESS(null)
                else {
                    _state.value = UiState.FAILURE(
                        it.errorBody()?.let { e -> NetworkUtil.getErrorResponse(e)?.message }.toString()
                    )
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}