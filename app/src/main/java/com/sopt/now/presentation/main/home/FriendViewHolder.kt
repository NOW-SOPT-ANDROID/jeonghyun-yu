package com.sopt.now.presentation.main.home

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.sopt.now.databinding.ItemMyFriendsBinding
import com.sopt.now.model.Profile


class FriendViewHolder(private val binding: ItemMyFriendsBinding): RecyclerView.ViewHolder(binding.root) {

    fun onBind(profile: Profile.FriendProfile) {
        binding.run {
            ivProfile.load(profile.img) {
                transformations(RoundedCornersTransformation(30f))
            }
            tvUsername.text = profile.username
        }
    }
}