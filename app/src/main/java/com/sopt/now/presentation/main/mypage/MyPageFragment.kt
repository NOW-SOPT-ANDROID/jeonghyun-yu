package com.sopt.now.presentation.main.mypage

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.model.info.UserInfo
import com.sopt.now.utils.Constants.Companion.MEMBER_ID

class MyPageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var memberId: String? = null
    private val myPageViewModel by viewModels<MyPageViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

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

    private fun getMemberId(): String? = arguments?.getString(MEMBER_ID)

    private fun getUserInfo() = memberId?.let { myPageViewModel.getUserInfo(it) }

    private fun observeUserInfo() {
        myPageViewModel.status.observe(viewLifecycleOwner) {
            if (it) {
                showUserInfo(myPageViewModel.getUserInfo())
            } else {
                showToast(myPageViewModel.getErrorMessage() ?: "")
            }
        }
    }

    private fun showUserInfo(data: UserInfo) {
        with(binding) {
            tvMainUserNickname.text = data.nickname
            tvMainUserId.text = data.authenticationId
            tvMainUserPhone.text = data.phone
        }
    }

    private fun showToast(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}