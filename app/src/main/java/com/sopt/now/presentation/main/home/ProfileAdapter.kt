package com.sopt.now.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.R
import com.sopt.now.model.Profile
import com.sopt.now.databinding.ItemMyFriendsBinding
import com.sopt.now.databinding.ItemMyFriendsWithMusicBinding
import com.sopt.now.databinding.ItemMyProfileBinding

class ProfileAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var friendProfile: List<Profile>

    fun setFriendList(friendList: List<Profile>) {
        this.friendProfile = friendList.toList()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (friendProfile[position]) {
        is Profile.MyProfile -> R.layout.item_my_profile
        is Profile.FriendProfile -> R.layout.item_my_friends
        is Profile.FriendProfileWithMusic -> R.layout.item_my_friends_with_music
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            R.layout.item_my_profile -> MyProfileViewHolder(ItemMyProfileBinding.inflate(layoutInflater, parent, false))
            R.layout.item_my_friends -> FriendViewHolder(ItemMyFriendsBinding.inflate(layoutInflater, parent, false))
            else -> FriendWithMusicViewHolder(ItemMyFriendsWithMusicBinding.inflate(layoutInflater, parent,false))
        }
    }

    override fun getItemCount(): Int = friendProfile.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = friendProfile[position]

        when(holder) {
            is MyProfileViewHolder -> holder.onBind(item as Profile.MyProfile)
            is FriendViewHolder -> holder.onBind(item as Profile.FriendProfile)
            is FriendWithMusicViewHolder -> holder.onBind(item as Profile.FriendProfileWithMusic)
        }
    }
}