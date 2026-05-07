package com.example.smsrly.data.local.datasource.realestatelocaldatasource

import com.example.smsrly.data.local.db.entities.RealEstateEntity
import com.example.smsrly.domain.models.RealEstate
import kotlinx.coroutines.flow.Flow

interface IRealEstateLocalDataSource {
    suspend fun addRealEstates(realEstates:List<RealEstateEntity>)
    fun getRealEstates():  Flow<List<RealEstateEntity>>
    suspend fun getNearestRealEstates():Flow<List<RealEstateEntity>>
    suspend fun saveARealEstate(id:Int)
    suspend fun unSaveARealEstate(id:Int)
    fun getUserSaved():Flow<List<RealEstateEntity>>
    suspend fun sendRequest(id: Int)
    suspend fun cancelRequest(id:Int)
    fun getRealEstateById(id:Int):Flow<RealEstateEntity>

    fun getUserRequests():Flow<List<RealEstateEntity>>
}