package com.example.smsrly.utility

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class ImageUtils @Inject constructor(
    @ApplicationContext private val  context: Context
) {
    fun convertImageUriToJpgFile(uri: Uri): File? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)

            val file = File(context.cacheDir, "image_${System.currentTimeMillis()}.jpg")
            val outputStream = FileOutputStream(file)


            var quality = 90
            do {
                outputStream.flush()
                outputStream.channel.truncate(0)
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
                quality -= 10
            } while (file.length() > 900_000 && quality > 10)

            outputStream.close()
            file
        } catch (e: Exception) {

            null
        }
    }
}