package com.example.smsrly.domain.repository

import com.example.smsrly.domain.models.RealEstate
import kotlinx.coroutines.flow.Flow


interface IRealEstateRepo{
    suspend fun uploadARealState(realState: RealEstate):Result<Int>
   suspend fun uploadRealEstateImage(images: List<String>,id:Int) :Result<Int>
    suspend fun getAllRealEstates(): Flow<List<RealEstate>>
    suspend fun getNeatestRealEstates():Flow<List<RealEstate>>
    suspend fun getUserUploads():Result<List<RealEstate>>
    suspend fun sendRequest(id:Int): Result<String>
    suspend fun saveARealEstate(id:Int):Result<String>
    suspend fun unSaveARealEstate(id:Int):Result<String>
    fun getUserSavedRealEstates():Flow<List<RealEstate>>
    suspend fun cancelRequest(id:Int):Result<String>
    fun getUserRequests():Flow<List<RealEstate>>
    fun resetData()
    suspend fun searchByTitle(title: String):Result<List<RealEstate>>
    fun getRealEstateById(id:Int):Flow<RealEstate>

}
