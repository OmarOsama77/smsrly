package com.example.smsrly.presentation.ui.screens.auth.otp.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.smsrly.R
import com.example.smsrly.presentation.ui.screens.auth.otp.viewmodel.OtpViewModel

@Composable
fun UserImagePicker(viewModel: OtpViewModel) {
    val imageUri = viewModel.selectedImage.collectAsState()

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        viewModel.setImage(uri)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                imagePickerLauncher.launch("image/*")
            },
        contentAlignment = Alignment.Center
    ) {
        if (imageUri.value == null) {
            Image(
                painter = painterResource(R.drawable.anonymous),
                contentDescription = null,
                modifier = Modifier.size(90.dp)
            )
        } else {

            AsyncImage(
                model = imageUri.value,
                contentDescription = "Selected profile image",
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape),
                placeholder = painterResource(R.drawable.anonymous),
                contentScale = ContentScale.Crop
            )
        }
    }
}