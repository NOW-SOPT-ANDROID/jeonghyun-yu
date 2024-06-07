package com.sopt.now.compose.presentation.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.model.signup.RequestSignUpDto
import com.sopt.now.compose.utils.NetworkUtil
import com.sopt.now.compose.utils.ServicePool.authService
import com.sopt.now.compose.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val _state = MutableStateFlow<UiState>(UiState.LOADING)
    val state = _state.asStateFlow()

    fun signUp(data: RequestSignUpDto) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = UiState.LOADING
            runCatching {
                authService.signUp(data)
            }.onSuccess {
                if (it.isSuccessful) _state.value = UiState.SUCCESS<Unit>()
                else {
                    _state.value = UiState.FAILURE(
                        it.errorBody()?.let { e -> NetworkUtil.getErrorResponse(e)?.message }
                            .toString()
                    )
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}