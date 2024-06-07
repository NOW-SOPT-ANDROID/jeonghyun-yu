package com.sopt.now.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.data.AuthRepositoryImpl
import com.sopt.now.presentation.auth.login.LoginViewModel
import com.sopt.now.utils.ServicePool

class LoginViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            val repository = AuthRepositoryImpl(ServicePool.loginService)
            return LoginViewModel(repository) as T
        } else {
            throw IllegalArgumentException("fail ${modelClass.name}")
        }
    }
}