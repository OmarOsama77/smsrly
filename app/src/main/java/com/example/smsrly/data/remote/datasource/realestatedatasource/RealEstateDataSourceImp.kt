package com.example.smsrly.data.remote.datasource.realestatedatasource

import android.util.Log
import androidx.core.net.toUri
import com.example.smsrly.data.remote.apiservice.ApiService
import com.example.smsrly.data.remote.dto.errorhandling.FailedDto
import com.example.smsrly.data.remote.dto.realestate.getrealestates.GetRealEstatesDto
import com.example.smsrly.data.remote.dto.realestate.getuseruploads.GetUserUploadsDto
import com.example.smsrly.data.remote.dto.realestate.savearealestate.SaveARealEstateDto
import com.example.smsrly.data.remote.dto.realestate.searchrealestate.SearchDto
import com.example.smsrly.data.remote.dto.realestate.sendRequest.SuccessfulRequestDto
import com.example.smsrly.data.remote.dto.realestate.uploadRealState.UploadRealStateDto
import com.example.smsrly.data.remote.dto.realestate.uploadRealState.uploadrealestateimage.UploadRealEstateImageFailedDto
import com.example.smsrly.utility.ImageUtils
import com.squareup.moshi.Moshi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import javax.inject.Inject

class RealEstateDataSourceImp @Inject constructor(
    private val apiService: ApiService,
    private val moshi: Moshi,
    private val imageUtils: ImageUtils
) : IRealEstateDataSource {
    override suspend fun uploadARealState(realStateDto: UploadRealStateDto): Result<Int> {
        try {
            val response = apiService.uploadRealState(realStateDto)
            if (response.isSuccessful) {
                val id: Int = response.body()?.message!!.toInt()
                return Result.success(id)
            } else {
                val errorJson = response.errorBody()?.string()
                errorJson?.let {
                    val adapter = moshi.adapter(FailedDto::class.java)
                    val errorResponse = adapter.fromJson(it)
                    return Result.failure(Exception(errorResponse?.error?.message?:"Failed"))
                }
                return Result.failure(Exception("Failed to upload"))
            }
        } catch (e: Exception) {

            return Result.failure(Exception("Network issue"))
        }
    }

    override suspend fun uploadRealEstateImage(image: String, id: Int): Result<String> {
       try{
           val imageFile = imageUtils.convertImageUriToJpgFile(image.toUri())
           val requestFile = imageFile!!.asRequestBody("image/*".toMediaType())

           val imagePart = MultipartBody.Part.createFormData(
               name = "image",
               filename = imageFile.name,
               body = requestFile
           )
           val res = apiService.uploadRealEstateImage(imagePart, id)

           if (res.isSuccessful) {
               return Result.success(res.body()!!.message)
           } else {
               val errorJson = res.errorBody()?.string()

               if (errorJson.isNullOrEmpty()) {
                   return Result.failure(Exception("Failed to upload image"))
               }
               val adapter = moshi.adapter(UploadRealEstateImageFailedDto::class.java)
               val errorResponse = adapter.fromJson(errorJson)
               return Result.failure(Exception(errorResponse?.error?.message))
           }
       }catch (e: Exception){
           return Result.failure(Exception("Network issue"))
       }
    }

    override suspend fun getAllRealEstates(): Result<GetRealEstatesDto> {
        try{
            val res = apiService.getAllRealEstates()
            if(res.isSuccessful){
                return Result.success(res.body()!!)
            }
            return Result.failure(Exception("Failed to fetch"))
        }catch (e: Exception){
            return Result.failure(Exception("Network issue"))
        }
    }



    override suspend fun getNearestRealEstates(): Result<GetRealEstatesDto> {
        try{
            val res = apiService.getNearestRealEstates()
            if(res.isSuccessful){
                return Result.success(res.body()!!)
            }
            return Result.failure(Exception("Failed to fetch"))
        }catch (e: Exception){
            return Result.failure(Exception("Network issue"))
        }
    }

    override suspend fun getUserUploads(): Result<GetUserUploadsDto> {
       try{
           val res = apiService.getUserUploads()
           if(res.isSuccessful){
             return Result.success(res.body()!!)
           }
           return Result.failure(Exception("Failed to fetch"))
       }catch (e : Exception){
           return Result.failure(Exception("Network issue"))
       }
    }

    override suspend fun sendRequest(id: Int): Result<SuccessfulRequestDto> {
        try{
            val res = apiService.sendRequest(id)
            if(res.isSuccessful){
                return Result.success(res.body()!!)
            }
            val errorJson = res.errorBody()?.string()

            errorJson?.let {
                val adapter = moshi.adapter(FailedDto::class.java)
                val errorResponse = adapter.fromJson(it)
                return Result.failure(Exception(errorResponse?.error?.message?:"Failed"))
            }
            return Result.failure(Exception("Failed to send request"))
        }catch (e: Exception){
            Log.d("ya omar the res is the issue ",e.toString())
            return Result.failure(Exception("Network issue"))
        }
    }

    override suspend fun saveARealEstate(id: Int): Result<SaveARealEstateDto> {
        try{
            val res = apiService.saveARealEstate(id)
            if(res.isSuccessful){
                return Result.success(res.body()!!)
            }
            val errorJson = res.errorBody()?.string()

            errorJson?.let {
                val adapter = moshi.adapter(FailedDto::class.java)
                val errorResponse = adapter.fromJson(it)
                return Result.failure(Exception(errorResponse?.error?.message?:"Failed"))
            }
            return Result.failure(Exception("Failed to send request"))
        }catch (e : Exception){
            return Result.failure(Exception("Network issue"))
        }
    }

    override suspend fun unSaveARealEstate(id: Int): Result<SaveARealEstateDto> {
        try{
            val res = apiService.unSaveARealEstate(id)
            if(res.isSuccessful){
                return Result.success(res.body()!!)
            }
            val errorJson = res.errorBody()?.string()

            errorJson?.let {
                val adapter = moshi.adapter(FailedDto::class.java)
                val errorResponse = adapter.fromJson(it)
                return Result.failure(Exception(errorResponse?.error?.message?:"Failed"))
            }
            return Result.failure(Exception("Failed to unsave"))
        }catch (e : Exception){
            return Result.failure(Exception("Network issue"))
        }
    }

    override suspend fun getUserSavedRealEstates(): Result<GetRealEstatesDto> {
        try{
            val res = apiService.getUserSavedRealEstates()
            if(res.isSuccessful){
                return Result.success(res.body()!!)
            }
            return Result.failure(Exception("Failed to get user saves"))
        }catch (e: Exception){
            return Result.failure(Exception("Network issue"))
        }
    }

    override suspend fun cancelRequest(id:Int): Result<SuccessfulRequestDto> {
        try{
            val res = apiService.deleteRequest(id)
            if(res.isSuccessful){
                return Result.success(res.body()!!)
            }
            return Result.failure(Exception("failed to remove request"))
        }catch (e : Exception){
            return Result.failure(Exception("Network issue"))
        }
    }

    override suspend fun getUserRequests(): Result<GetRealEstatesDto> {
         try{
             val res = apiService.getUserRequests()
             if(res.isSuccessful){
                 return Result.success(res.body()!!)
             }
             return Result.failure(Exception("Failed getting user requests"))
         }catch (e : Exception){
             return Result.failure(Exception("Network issue"))
         }
    }

    override suspend fun searchByTitle(title:String): Result<GetRealEstatesDto> {
        try{
            val body = SearchDto(title)
            val res = apiService.searchByTitle(body)
            if(res.isSuccessful){
                return Result.success(res.body()!!)
            }
            return Result.failure(Exception("Failed searching"))
        }catch (e : Exception){

            return Result.failure(Exception("Network issue"))
        }
    }


}