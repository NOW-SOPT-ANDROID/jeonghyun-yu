package com.sopt.now.presentation.main.home

import androidx.lifecycle.ViewModel
import com.sopt.now.R
import com.sopt.now.data.Friend

class HomeViewModel: ViewModel() {
    val mockFriendList = listOf<Friend> (
        Friend(R.drawable.ic_person_black_24, "유정현", "Sdfsadfasdfasdfasdf"),
        Friend(R.drawable.ic_person_black_24, "유정현", "Sdfsadfasdfasdfasdf"),
        Friend(R.drawable.ic_person_black_24, "유정현", "Sdfsadfasdfasdfasdf"),
        Friend(R.drawable.ic_person_black_24, "유정현", "Sdfsadfasdfasdfasdf")
    )
}