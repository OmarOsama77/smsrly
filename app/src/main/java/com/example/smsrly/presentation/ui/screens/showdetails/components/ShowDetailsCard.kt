package com.example.smsrly.presentation.ui.screens.showdetails.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smsrly.R
import com.example.smsrly.domain.models.UserInfo
import com.example.smsrly.presentation.ui.screens.chats.ChatsRoute
import com.example.smsrly.presentation.ui.screens.chats.conversation.ConversationRoute
import com.example.smsrly.presentation.ui.screens.components.UserImage

@Composable
fun ShowDetailsCard(
    navController: NavController,
    uploaderInfo : UserInfo,
    desc : String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(12.dp),
            )
            .background(color = Color(0xFFFFFFFF))
    ) {
        Column(
            modifier = Modifier.padding(top = 12.dp, start = 8.dp, end = 8.dp, bottom = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    UserImage(uploaderInfo.userImage)
                    Spacer(Modifier.width(5.dp))
                    Text(uploaderInfo.userName)


                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton({

                        navController.navigate(ConversationRoute(uploaderInfo))
                    }, modifier = Modifier.height(30.dp).width(40.dp) ) {
                        Icon(
                            painter = painterResource(R.drawable.chatunselected),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )

                    }
                    Spacer(Modifier.width(5.dp))
                    IconButton({}, modifier = Modifier.height(30.dp).width(40.dp)) {
                        Icon(
                            painter = painterResource(R.drawable.phone),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.height(5.dp))
            Text(desc)
        }
    }
}