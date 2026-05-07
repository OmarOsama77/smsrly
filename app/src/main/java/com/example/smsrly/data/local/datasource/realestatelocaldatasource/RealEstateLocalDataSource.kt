package com.example.smsrly.data.local.datasource.realestatelocaldatasource

import android.util.Log
import com.example.smsrly.data.local.db.SmsrlyDataBaseDao
import com.example.smsrly.data.local.db.entities.RealEstateEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RealEstateLocalDataSource @Inject constructor(
    private val db: SmsrlyDataBaseDao
) : IRealEstateLocalDataSource {
    override suspend fun addRealEstates(realEstates: List<RealEstateEntity>) {
        try {
            db.addRealEstates(realEstates)
        } catch (e: Exception) {
            Log.d("i have an error ", e.toString())
        }

    }

    override fun getRealEstates(): Flow<List<RealEstateEntity>> {
        return db.getRealEstates()
    }

    override suspend fun getNearestRealEstates(
    ): Flow<List<RealEstateEntity>> {
        return db.getNearestRealEstates()
    }

    override suspend fun saveARealEstate(id: Int) {
        db.saveARealEstate(id)
    }

    override suspend fun unSaveARealEstate(id: Int) {
        db.unSaveARealEstate(id)
    }

    override fun getUserSaved(): Flow<List<RealEstateEntity>> {
        return db.getUserSaved()
    }

    override suspend fun sendRequest(id: Int) {
        db.requestARealEstate(id)
    }

    override suspend fun cancelRequest(id: Int) {
       db.unRequestARealEstate(id)
    }

    override fun getRealEstateById(id: Int): Flow<RealEstateEntity> {
        return db.getRealEstateById(id)
    }

    override fun getUserRequests(): Flow<List<RealEstateEntity>> {
        return db.getUserRequests()
    }

}