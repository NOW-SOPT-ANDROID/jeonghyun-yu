package com.sopt.now.presentation.main.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.model.info.UserInfo
import com.sopt.now.utils.NetworkUtil
import com.sopt.now.utils.ServicePool.infoService
import com.sopt.now.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {
    private val _state = MutableStateFlow<UiState>(UiState.LOADING)
    val state = _state.asStateFlow()

    fun getUserInfo() {
        _state.value = UiState.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                infoService.getUserInfo()
            }.onSuccess {
                if (it.isSuccessful) {
                    _state.value = UiState.SUCCESS<UserInfo>(
                        it.body()?.data
                    )
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
