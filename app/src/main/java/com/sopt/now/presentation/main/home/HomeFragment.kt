package com.sopt.now.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sopt.now.R
import com.sopt.now.config.BaseFragment
import com.sopt.now.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val friendAdapter = ProfileAdapter()
        binding.rvFriend.adapter = friendAdapter
        friendAdapter.setFriendList(viewModel.mockFriendList)
    }
}
