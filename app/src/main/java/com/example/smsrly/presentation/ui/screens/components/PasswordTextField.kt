package com.example.smsrly.presentation.ui.screens.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smsrly.R

@Composable
fun PasswordTextField(label : String,text:String,onTextChange:(String)->Unit) {
    Text(label, fontSize = 15.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(10.dp))
    var showText by remember { mutableStateOf(false) }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        placeholder = {
            Text(label)
        },
        onValueChange = { newTxt ->
            onTextChange(newTxt)
        },
        shape = RoundedCornerShape(12.dp),
        trailingIcon = {
            IconButton({
                showText = !showText
            }) {
                Icon(
                    painter = painterResource(id = if (showText) R.drawable.openeye else R.drawable.hide),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
            }
        },
        visualTransformation = if (showText) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFE0E0E0),
            unfocusedContainerColor = Color(0xFFE0E0E0),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
        ),

        )
}