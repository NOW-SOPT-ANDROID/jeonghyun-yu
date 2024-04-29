package com.sopt.now.presentation.main.home

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.sopt.now.model.Profile
import com.sopt.now.databinding.ItemMyFriendsWithMusicBinding

class FriendWithMusicViewHolder(private val binding: ItemMyFriendsWithMusicBinding): RecyclerView.ViewHolder(binding.root) {
    fun onBind(profile: Profile.FriendProfileWithMusic) {
        binding.run {
            ivProfile.load(profile.img) {
                transformations(RoundedCornersTransformation(30f))
            }
            tvUsername.text = profile.username
            tvMusic.text = profile.music
        }
    }
}