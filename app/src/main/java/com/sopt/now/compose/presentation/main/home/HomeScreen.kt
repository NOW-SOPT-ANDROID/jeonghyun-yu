package com.sopt.now.compose.presentation.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.model.Profile

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    val homeViewModel = HomeViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(homeViewModel.mockFriendList) { profile ->
                when (profile) {
                    is Profile.MyProfile -> MyProfileItem(profile = profile)
                    is Profile.FriendProfile -> FriendProfileItem(profile = profile)
                    is Profile.FriendProfileWithMusic -> FriendProfileWithMusicItem(profile = profile)
                }
            }
        }
    }
}

@Composable
fun MyProfileItem(profile: Profile.MyProfile) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card {
            Image(
                painter = painterResource(id = profile.img),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .aspectRatio(1f / 1f)
            )
        }

        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "${profile.username}"
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "${profile.description}",
        )
        Spacer(modifier = Modifier.width(10.dp))
        Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = null)
    }
    Spacer(modifier = Modifier.height(10.dp))
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp),
        color = Color(0xFFDDDDDD)
    ) {}
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun FriendProfileItem(profile: Profile.FriendProfile) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card {
            Image(
                painter = painterResource(id = profile.img),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .aspectRatio(1f / 1f)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "${profile.username}"
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun FriendProfileWithMusicItem(profile: Profile.FriendProfileWithMusic) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card {
            Image(
                painter = painterResource(id = profile.img),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .aspectRatio(1f / 1f)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "${profile.username}"
        )
        Spacer(modifier = Modifier.weight(1f))
        CustomText(profile.music)
        Spacer(modifier = Modifier.size(10.dp))
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun CustomText(text: String) {
    Row(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.Green,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(10.dp, 3.dp, 5.dp, 3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = 13.sp
        )
        Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = null)
    }
}