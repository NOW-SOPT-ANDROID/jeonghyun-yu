package com.sopt.now.utils

sealed class UiState {
    data object LOADING : UiState()
    data class SUCCESS<T>(
        val data: T? = null
    ) : UiState()

    data class FAILURE(
        val errorMessage: String
    ) : UiState()
}
