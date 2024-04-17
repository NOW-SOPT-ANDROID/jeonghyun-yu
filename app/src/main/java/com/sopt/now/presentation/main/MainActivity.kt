package com.sopt.now.presentation.main

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sopt.now.R
import com.sopt.now.data.SignUpData
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.presentation.main.home.HomeFragment
import com.sopt.now.presentation.main.mypage.MyPageFragment
import com.sopt.now.presentation.main.search.SearchFragment
import com.sopt.now.utils.Constants
import com.sopt.now.utils.Constants.Companion.USER_DATA


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var userData: SignUpData? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        val currentFragment = supportFragmentManager.findFragmentById(binding.fcvMain.id)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.fcvMain.id, HomeFragment())
                .commit()
        }

        clickBottomNavigation()
        userData = getUserInfo()
    }

    private fun clickBottomNavigation() {
        binding.bnvMain.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.menu_search -> {
                    replaceFragment(SearchFragment())
                    true
                }
                R.id.menu_mypage -> {
                    replaceFragment(MyPageFragment().apply {
                        arguments = Bundle().apply {
                            putParcelable(USER_DATA, userData)
                        }
                    })
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun getUserInfo(): SignUpData? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getParcelableExtra(USER_DATA, SignUpData::class.java)
        else intent.getParcelableExtra(USER_DATA)
    }

}