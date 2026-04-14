package com.example.smsrly.domain.usecase.realstateusecase

import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.repository.IRealEstateRepo


import javax.inject.Inject

class UploadRealStateUseCase @Inject constructor(
    private val realEstateRepo: IRealEstateRepo,
) {

    suspend fun uploadRealState(realState: RealEstate): Result<String> {
        val res = validation(realState)
        if (res == "Validation passed") {
            val res = realEstateRepo.uploadARealState(realState)
            if (res.isSuccess) {
               val imageRes =  realEstateRepo.uploadRealEstateImage(realState.images,res.getOrNull()!!)
               if(imageRes.isSuccess){
                   return Result.success("RealEstate uploaded with ${imageRes.getOrNull()} images")
               }else{
                   return Result.failure(imageRes.exceptionOrNull()!!)
               }
            }else{
                return Result.failure(res.exceptionOrNull()!!)
            }
        }else{
            return Result.failure(Exception(res))
        }
    }



    fun validation(realState: RealEstate): String {
        if (realState.title.length < 10 || realState.title.length > 50) {
            return "title must be between 10 and 50 characters"
        }
        if (realState.area < 10) {
            return "Area must be greater than 10"
        }
        if (realState.desc.length < 10) {
            return "description should be at least 10 characters"
        }

        if (realState.floor < 0) {
            return "Floor number cannot be negative"
        }
        if (realState.rooms <= 0) {
            return "Number of rooms must be greater than or equal to 1"
        }
        if (realState.bathRoom <= 0) {
            return "Number of bathrooms must be greater than 0"
        }
        if (realState.price < 1000) {
            return "Price must be greater than 1000"
        }

        if (realState.latitude !in -90.0..90.0) {
            return "Latitude must be between -90 and 90"
        }
        if (realState.longitude !in -180.0..180.0) {
            return "Longitude must be between -180 and 180"
        }

        return "Validation passed"
    }
}
