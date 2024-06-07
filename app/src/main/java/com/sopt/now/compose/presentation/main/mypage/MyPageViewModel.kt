package com.sopt.now.compose.presentation.main.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.utils.NetworkUtil
import com.sopt.now.compose.utils.ServicePool.infoService
import com.sopt.now.compose.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {
    private val _state = MutableStateFlow<UiState>(UiState.LOADING)
    val state = _state.asStateFlow()

    fun getUserInfo() {
        viewModelScope.launch {
            _state.value = UiState.LOADING
            runCatching {
                infoService.getUserInfo()
            }.onSuccess {
                if (it.isSuccessful) {
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