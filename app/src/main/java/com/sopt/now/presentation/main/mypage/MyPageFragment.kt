package com.sopt.now.presentation.main.mypage

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.model.SignUpData
import com.sopt.now.model.info.ResponseGetInfoDto
import com.sopt.now.model.info.UserInfo
import com.sopt.now.utils.Constants.Companion.MEMBER_ID
import com.sopt.now.utils.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var memberId: String = ""
    private val myPageViewModel by viewModels<MyPageViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        memberId = getMemberId()
        getUserInfo()
        observeUserInfo()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun getMemberId(): String = arguments?.getString(MEMBER_ID)!!

    private fun getUserInfo() = myPageViewModel.getUserInfo(memberId)

    private fun observeUserInfo() {
        myPageViewModel.userInfo.observe(viewLifecycleOwner) {
            showUserInfo(it)
        }
    }

    private fun showUserInfo(data: UserInfo) {
        with(binding) {
            tvMainUserNickname.text = data.nickname
            tvMainUserId.text = data.authenticationId
            tvMainUserPhone.text = data.phone
        }
    }
}