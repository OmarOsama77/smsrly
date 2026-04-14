package com.example.smsrly.domain.repository

import com.example.smsrly.data.remote.dto.realestate.getuseruploads.GetUserUploadsDto
import com.example.smsrly.domain.models.RealEstate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


interface IRealEstateRepo{
    suspend fun uploadARealState(realState: RealEstate):Result<Int>
   suspend fun uploadRealEstateImage(images: List<String>,id:Int) :Result<Int>
    suspend fun getAllRealEstates(): Result<String>
    suspend fun getNeatestRealEstates():Result<String>
    suspend fun getUserUploads():Result<List<RealEstate>>
    suspend fun sendRequest(id:Int): Result<String>
    fun getRealEstateObj():StateFlow<Map<Int, RealEstate>>
    fun getNearestRealEstateObj():StateFlow<Map<Int, RealEstate>>
    suspend fun saveARealEstate(id:Int):Result<String>
    suspend fun unSaveARealEstate(id:Int):Result<String>
    fun getUserSavedRealEstates():Flow<List<RealEstate>>
      fun toggleRequested (id:Int,isRequested:Boolean)
      fun toggleSaved(id:Int,isSaved:Boolean)
    suspend fun cancelRequest(id:Int):Result<String>
      fun getUserRequests():Flow<List<RealEstate>>
    fun resetData()
    suspend fun searchByTitle(title: String):Result<List<RealEstate>>
}
