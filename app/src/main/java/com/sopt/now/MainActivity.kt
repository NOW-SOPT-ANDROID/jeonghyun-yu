package com.sopt.now

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var data: SignUpData? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data = getUserInfo()
        showUserInfo()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun getUserInfo() : SignUpData?{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra("data", SignUpData::class.java)
        else intent.getSerializableExtra("data") as SignUpData
    }

    private fun showUserInfo() {
        with(binding) {
            tvMainUserNickname.text = data?.nickname
            tvMainUserMbti.text = data?.mbti
            tvMainUserId.text = data?.id
            tvMainUserPw.text = data?.password
        }
    }
}