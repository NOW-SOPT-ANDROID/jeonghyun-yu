package com.sopt.now.presentation.main.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.model.info.UserInfo
import com.sopt.now.utils.UiState
import com.sopt.now.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MyPageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val myPageViewModel: MyPageViewModel by viewModels()

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

        getUserInfo()
        observeUserInfo()
    }

    private fun getUserInfo() {
        myPageViewModel.getUserInfo()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun observeUserInfo() {
        myPageViewModel.state.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { state ->
            when (state) {
                is UiState.LOADING -> {}
                is UiState.SUCCESS -> {
                    state.data?.let { showUserInfo(it) }
                }
                is UiState.FAILURE -> { requireContext().showToast(state.errorMessage) }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun showUserInfo(data: UserInfo) {
        with(binding) {
            tvMainUserNickname.text = data.nickname
            tvMainUserId.text = data.authenticationId
            tvMainUserPhone.text = data.phone
        }
    }
}