package com.sopt.now.utils

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.now.BuildConfig
import com.sopt.now.datasource.AuthService
import com.sopt.now.datasource.InfoService
import com.sopt.now.utils.ApiFactory.create
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object ApiFactory {
    private const val BASE_URL: String = BuildConfig.AUTH_BASE_URL

    private val client: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(5000, TimeUnit.MILLISECONDS)
        .connectTimeout(5000, TimeUnit.MILLISECONDS)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addNetworkInterceptor(XAccessTokenInterceptor())
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T =
        retrofit.create(T::class.java) // create를 통해서 retrofit 구현체 생성
}

object ServicePool {
    val authService: AuthService by lazy { create<AuthService>() }
    val loginService: AuthService by lazy { create<AuthService>() }
    val infoService: InfoService by lazy { create<InfoService>() }
}