package com.sopt.now.compose.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.compose.presentation.auth.signup.SignUpViewModel
import com.sopt.now.compose.utils.ServicePool

class SignUpViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            val repository = AuthRepositoryImpl(ServicePool.authService)
            return SignUpViewModel(repository) as T
        } else {
            throw IllegalArgumentException("fail ${modelClass.name}")
        }
    }
}