package com.example.smsrly.presentation.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smsrly.domain.models.User
import com.example.smsrly.presentation.ui.screens.components.UserImage
import com.example.smsrly.presentation.ui.screens.components.UserImageShimmer
import com.example.smsrly.presentation.ui.screens.components.UserNameShimmer
import com.example.smsrly.presentation.ui.screens.home.viewmodel.states.UserState
import com.example.smsrly.presentation.ui.theme.Primary

@Composable
fun HomeHeader(user: User?, userState : UserState) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text("Welcome,", color = Color(0xFF737373))
            if(userState is UserState.Success){
                Text(
                    "${user!!.firstName} ${user.lastName}",
                    color = Primary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }else if(userState is UserState.Loading){
                UserNameShimmer()
            }
        }
        if(userState is UserState.Success){
            UserImage(user!!.imageUrl)
        }else if(userState is UserState.Loading){
            UserImageShimmer()
        }
    }
    Spacer(Modifier.height(20.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Nearest By Location", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text("See all", color = Color(0xFF737373), fontSize = 15.sp)
    }
}