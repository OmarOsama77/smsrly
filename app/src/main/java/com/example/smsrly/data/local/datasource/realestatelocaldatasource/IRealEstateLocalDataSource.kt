package com.example.smsrly.data.local.datasource.realestatelocaldatasource

import com.example.smsrly.data.local.db.entities.RealEstateEntity

interface IRealEstateLocalDataSource {
    suspend fun addRealEstates(realEstates:List<RealEstateEntity>)
    suspend fun getRealEstates(): Result<List<RealEstateEntity>>
}