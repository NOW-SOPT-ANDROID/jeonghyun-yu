package com.sopt.now

import android.app.Application
import android.content.SharedPreferences
import com.sopt.now.ApplicationClass.SharedPreferences.editor
import com.sopt.now.ApplicationClass.SharedPreferences.sSharedPreferences

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