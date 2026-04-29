package com.example.smsrly.data.local.datasource.realestatelocaldatasource

import android.util.Log
import com.example.smsrly.data.local.db.SmsrlyDataBaseDao
import com.example.smsrly.data.local.db.entities.RealEstateEntity
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

    override suspend fun getRealEstates(): Result<List<RealEstateEntity>> {
        try {
            val data = db.getRealEstates()
            Log.d("The data ",data.toString())
            return Result.success(data)
        } catch (e: Exception) {
            Log.d("The resaon ",e.toString())
              return Result.failure(Exception("Failed getting data"))
       }
     }

}