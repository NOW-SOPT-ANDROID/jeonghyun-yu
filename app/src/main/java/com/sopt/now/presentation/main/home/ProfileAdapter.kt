package com.sopt.now.presentation.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sopt.now.R
import com.sopt.now.model.Profile
import com.sopt.now.databinding.ItemMyFriendsBinding
import com.sopt.now.databinding.ItemMyFriendsWithMusicBinding
import com.sopt.now.databinding.ItemMyProfileBinding
import com.sopt.now.utils.ItemDiffCallback

class ProfileAdapter(
    context: Context
) : ListAdapter<Profile, ViewHolder>(
    ItemDiffCallback<Profile>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old == new }
    )
) {
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            R.layout.item_my_profile -> MyProfileViewHolder(
                ItemMyProfileBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )

            R.layout.item_my_friends -> FriendViewHolder(
                ItemMyFriendsBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )

            else -> FriendWithMusicViewHolder(
                ItemMyFriendsWithMusicBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        when (getItemViewType(position)) {
            R.layout.item_my_profile -> (holder as MyProfileViewHolder).onBind(item as Profile.MyProfile)
            R.layout.item_my_friends -> (holder as FriendViewHolder).onBind(item as Profile.FriendProfile)
            R.layout.item_my_friends_with_music -> (holder as FriendWithMusicViewHolder).onBind(item as Profile.FriendProfileWithMusic)
        }
    }

    override fun getItemViewType(position: Int) : Int {
        return when (getItem(position)) {
            is Profile.MyProfile -> R.layout.item_my_profile
            is Profile.FriendProfile -> R.layout.item_my_friends
            is Profile.FriendProfileWithMusic -> R.layout.item_my_friends_with_music
        }
    }
}
