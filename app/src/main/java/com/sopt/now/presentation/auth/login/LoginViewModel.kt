package com.sopt.now.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.ApplicationClass.SharedPreferences.editor
import com.sopt.now.model.login.RequestLoginDto
import com.sopt.now.utils.Constants.Companion.MEMBER_ID
import com.sopt.now.utils.NetworkUtil
import com.sopt.now.utils.ServicePool.loginService
import com.sopt.now.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow<UiState>(UiState.LOADING)
    val state = _state.asStateFlow()

    fun postLogin(data: RequestLoginDto) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = UiState.LOADING
            runCatching {
                loginService.login(data)
            }.onSuccess {
                if (it.isSuccessful) {
                    editor.putString(MEMBER_ID, it.headers()["location"])
                    editor.commit()

                    _state.value = UiState.SUCCESS(null)
                } else {
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
