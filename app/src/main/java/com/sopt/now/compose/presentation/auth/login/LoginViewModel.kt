package com.sopt.now.compose.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.ApplicationClass.SharedPreferences.editor
import com.sopt.now.compose.model.login.RequestLoginDto
import com.sopt.now.compose.repository.AuthRepository
import com.sopt.now.compose.utils.Constants.Companion.MEMBER_ID
import com.sopt.now.compose.utils.NetworkUtil
import com.sopt.now.compose.utils.ServicePool.loginService
import com.sopt.now.compose.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow<UiState>(UiState.LOADING)
    val state = _state.asStateFlow()

    fun postLogin(data: RequestLoginDto) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = UiState.LOADING
            runCatching {
                authRepository.loginUser(data)
            }.onSuccess {
                if (it.isSuccessful) {
                    editor.putString(MEMBER_ID, it.headers()["location"])
                    editor.commit()

                    _state.value = UiState.SUCCESS(
                        it.body()?.data
                    )
                } else {
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