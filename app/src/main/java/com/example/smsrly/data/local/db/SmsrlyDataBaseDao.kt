package com.example.smsrly.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.smsrly.data.local.db.entities.RealEstateEntity
import com.example.smsrly.data.local.db.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SmsrlyDataBaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("DELETE FROM User")
    suspend fun clearUser()

    @Transaction
    suspend fun saveUser(user: UserEntity) {
        clearUser()
        insertUser(user)
    }

    @Query("SELECT * FROM User LIMIT 1")
    fun getUser(): Flow<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRealEstates(realEstates: List<RealEstateEntity>)

    @Query("select * from RealEstate")
    fun getRealEstates(): Flow<List<RealEstateEntity>>

    @Query("SELECT RealEstate.* FROM RealEstate, (SELECT * FROM User LIMIT 1) AS User ORDER BY (RealEstate.latitude - User.latitude) * (RealEstate.latitude - User.latitude) + (RealEstate.longitude - User.longitude) * (RealEstate.longitude - User.longitude) ASC")
    fun getNearestRealEstates(): Flow<List<RealEstateEntity>>

    @Query("UPDATE RealEstate SET isSaved = 1 WHERE id = :id")
    suspend fun saveARealEstate(id: Int)

    @Query("UPDATE RealEstate SET isSaved = 0 WHERE id = :id")
    suspend fun unSaveARealEstate(id: Int)

    @Query("SELECT * FROM RealEstate where isSaved=1")
    fun getUserSaved(): Flow<List<RealEstateEntity>>

    @Query("UPDATE RealEstate SET isRequested = 1 WHERE id = :id")
    suspend fun requestARealEstate(id: Int)

    @Query("UPDATE RealEstate SET isRequested = 0 WHERE id = :id")
    suspend fun unRequestARealEstate(id: Int)

    @Query("SELECT * FROM RealEstate WHERE id=:id")
    fun getRealEstateById(id: Int): Flow<RealEstateEntity>

    @Query("SELECT * FROM RealEstate WHERE isRequested = 1")
    fun getUserRequests(): Flow<List<RealEstateEntity>>
}