package com.example.smsrly.domain.usecase.authusecases

import com.example.smsrly.domain.repository.IAuthRepo
import java.io.File
import javax.inject.Inject

class UploadUserImageUseCase @Inject constructor(private val authRepo: IAuthRepo) {
   suspend fun uploadUserImage(accessToken:String , image: File):Result<String>{
        return authRepo.uploadUserImage(image,accessToken)
    }
}