package com.sopt.now.utils

import com.sopt.now.data.AuthRepositoryImpl
import com.sopt.now.data.MyPageRepositoryImpl
import com.sopt.now.datasource.AuthService
import com.sopt.now.datasource.InfoService
import com.sopt.now.repository.AuthRepository
import com.sopt.now.repository.MyPageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    fun provideAuthRepository(authService: AuthService): AuthRepository {
        return AuthRepositoryImpl(authService)
    }

    @Provides
    fun provideMyPageRepository(infoService: InfoService): MyPageRepository {
        return MyPageRepositoryImpl(infoService)
    }
}
