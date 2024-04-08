package com.sopt.now

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.Constants.Companion.USER_DATA
import com.sopt.now.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var userData: SignUpData? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userData = getUserInfo()
        showUserInfo()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun getUserInfo(): SignUpData? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getParcelableExtra(USER_DATA, SignUpData::class.java)
        else intent.getParcelableExtra(USER_DATA)
    }

    private fun showUserInfo() {
        with(binding) {
            tvMainUserNickname.text = userData?.nickname
            tvMainUserMbti.text = userData?.mbti
            tvMainUserId.text = userData?.id
            tvMainUserPw.text = userData?.password
        }
    }
}