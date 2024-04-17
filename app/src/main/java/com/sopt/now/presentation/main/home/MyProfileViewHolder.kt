package com.sopt.now.presentation.main.home

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.sopt.now.model.Profile
import com.sopt.now.databinding.ItemMyProfileBinding

class MyProfileViewHolder(private val binding: ItemMyProfileBinding): RecyclerView.ViewHolder(binding.root) {

    fun onBind(profile: Profile.MyProfile) {
        binding.run {
            ivProfile.load(profile.img) {
                transformations(RoundedCornersTransformation(30f))
            }
            tvUsername.text = profile.username
            tvDescription.text = profile.description
        }
    }
}