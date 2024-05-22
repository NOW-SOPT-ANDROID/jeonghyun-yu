package com.sopt.now.compose

import android.app.Application
import com.sopt.now.compose.ApplicationClass.SharedPreferences.editor
import com.sopt.now.compose.ApplicationClass.SharedPreferences.sSharedPreferences

class ApplicationClass: Application() {
    object SharedPreferences {
        lateinit var sSharedPreferences: android.content.SharedPreferences
        lateinit var editor: android.content.SharedPreferences.Editor
    }

    override fun onCreate() {
        super.onCreate()
        sSharedPreferences =
            applicationContext.getSharedPreferences("NOW_SOPT", MODE_PRIVATE)
        editor = sSharedPreferences.edit()
    }
}