package com.example.smsrly.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smsrly.data.local.db.entities.RealEstateEntity

@Dao
interface SmsrlyDataBaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRealEstates(data:List<RealEstateEntity>)
    @Query("select * from RealEstate")
    suspend fun getRealEstates():List<RealEstateEntity>
}