package com.sopt.now.presentation.main.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.model.info.UserInfo
import com.sopt.now.repository.MyPageRepository
import com.sopt.now.utils.NetworkUtil
import com.sopt.now.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val myPageRepository: MyPageRepository
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<UserInfo>>(UiState.LOADING)
    val state get() = _state.asStateFlow()

    fun getUserInfo() {
        _state.value = UiState.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                myPageRepository.getUserInfo()
            }.onSuccess {
                if (it.isSuccessful) {
                    _state.value = UiState.SUCCESS(
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
