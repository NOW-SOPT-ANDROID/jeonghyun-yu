package com.sopt.now.compose.presentation.main.home

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.R
import com.sopt.now.compose.model.Profile

class HomeViewModel: ViewModel() {
    val mockFriendList = listOf<Profile>(
        Profile.MyProfile("유정현", R.drawable.img_jeonghyun, "안드로이드 34기 YB 유정현입니다"),
        Profile.FriendProfile("공세영", R.drawable.img_profile_2),
        Profile.FriendProfile("곽의진", R.drawable.img_profile_3),
        Profile.FriendProfile("김명석", R.drawable.img_profile_4),
        Profile.FriendProfile("김윤서", R.drawable.img_profile_1),
        Profile.FriendProfileWithMusic("박동민", R.drawable.img_profile_6, "조깅 - Lucy"),
        Profile.FriendProfile("박유진", R.drawable.img_profile_5),
        Profile.FriendProfile("배지현", R.drawable.img_profile_1),
        Profile.FriendProfile("배찬우", R.drawable.img_profile_2),
        Profile.FriendProfile("송혜음", R.drawable.img_profile_3),
        Profile.FriendProfile("윤서희", R.drawable.img_profile_4),
        Profile.FriendProfile("이가을", R.drawable.img_profile_5),
        Profile.FriendProfileWithMusic("이나경", R.drawable.img_profile_6, "Last Chance - 소수빈"),
        Profile.FriendProfileWithMusic("이유빈", R.drawable.img_profile_1, "Siren - Rize"),
        Profile.FriendProfile("임하늘", R.drawable.img_profile_2),
        Profile.FriendProfile("주효은", R.drawable.img_profile_3),
        Profile.FriendProfileWithMusic("최준서", R.drawable.img_profile_4, "가습기 - 한요한"),
        Profile.FriendProfileWithMusic("현진", R.drawable.img_profile_5, "사랑으로 - wave to earth"),
        Profile.FriendProfile("효빈", R.drawable.img_profile_6),
    )
}