package com.example.smsrly.presentation.ui.screens.myadds.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.smsrly.R
import com.example.smsrly.presentation.ui.screens.components.UserImage

@Composable
fun RequestedUsers(imageUrl:String?, name: String) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            .height(79.dp)
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(12.dp),
            )
            .background(color = Color(0xFFFFFFFF))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                UserImage(imageUrl)
                Spacer(modifier = Modifier.width(10.dp))
                Text(name)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton({

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
    }
}