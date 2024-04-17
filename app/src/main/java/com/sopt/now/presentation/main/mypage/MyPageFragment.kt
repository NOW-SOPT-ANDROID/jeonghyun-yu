package com.sopt.now.presentation.main.mypage

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.sopt.now.model.SignUpData
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.utils.Constants.Companion.USER_DATA

class MyPageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var userData: SignUpData? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userData = getUserInfo()
        Log.d("olivia userData", userData.toString())
        showUserInfo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun getUserInfo(): SignUpData? {
        /*return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            arguments?.getParcelable(USER_DATA, SignUpData::class.java)
        else arguments?.getParcelable(USER_DATA)*/
        return arguments?.getParcelable(USER_DATA)
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