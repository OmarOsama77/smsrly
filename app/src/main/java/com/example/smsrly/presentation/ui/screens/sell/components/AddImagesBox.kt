package com.example.smsrly.presentation.ui.screens.sell.components

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smsrly.presentation.ui.screens.sell.viewmodel.SellViewModel

@Composable
fun AddImagesBox(modifier: Modifier = Modifier,viewModel: SellViewModel) {
    val images = viewModel.selectedImages.collectAsState()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 3),
        onResult = { uris: List<Uri> ->
            if (uris.isNotEmpty()) {
                viewModel.addImages(uris)
            }
        }
    )
   if(images.value.isEmpty()){
       Box(
           modifier = modifier
               .clickable {
                   launcher.launch(
                       PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                   )
               }
               .height(151.dp)
               .fillMaxWidth()
               .border(
                   width = 1.dp,
                   color = Color.Gray,
                   shape = RoundedCornerShape(16.dp)
               ),
           contentAlignment = Alignment.Center,

           ){
           Text("Press to add Images", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
       }
   }else{
       ImagesSwiper(viewModel,{
           launcher.launch(
               PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
           )
           Log.d("im clicked btw","hey")
       })
   }
}