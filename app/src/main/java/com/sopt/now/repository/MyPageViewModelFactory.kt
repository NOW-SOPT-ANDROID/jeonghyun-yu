package com.sopt.now.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.data.MyPageRepositoryImpl
import com.sopt.now.presentation.main.mypage.MyPageViewModel
import com.sopt.now.utils.ServicePool

class MyPageViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyPageViewModel::class.java)) {
            val repository = MyPageRepositoryImpl(ServicePool.infoService)
            return MyPageViewModel(repository) as T
        } else {
            throw IllegalArgumentException("fail ${modelClass.name}")
        }
    }
}