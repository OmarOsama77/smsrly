package com.example.smsrly.data.local.datasource.realestatelocaldatasource

import android.util.Log
import com.example.smsrly.data.local.db.SmsrlyDataBaseDao
import com.example.smsrly.data.local.db.entities.RealEstateEntity
import javax.inject.Inject

class RealEstateLocalDataSource@Inject constructor(
    private val db : SmsrlyDataBaseDao
) : IRealEstateLocalDataSource {
    override suspend fun addRealEstates(realEstates: List<RealEstateEntity>) {
     try{
         Log.d("ya omar im cacshin ","Hey")
         db.addRealEstates(realEstates)
     }catch (e : Exception){
         Log.d("i have an error ",e.toString())
     }

    }

    override suspend fun getRealEstates(): List<RealEstateEntity> {
       return db.getRealEstates()
    }

}