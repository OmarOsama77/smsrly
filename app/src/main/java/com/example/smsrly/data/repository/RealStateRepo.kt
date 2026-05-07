package com.example.smsrly.data.repository

import android.util.Log
import com.example.smsrly.data.local.datasource.realestatelocaldatasource.IRealEstateLocalDataSource
import com.example.smsrly.data.local.db.entities.RealEstateEntity
import com.example.smsrly.data.mapper.toDB
import com.example.smsrly.data.mapper.toDomain
import com.example.smsrly.data.mapper.toUploadDto
import com.example.smsrly.data.remote.datasource.realestateremotedatasource.IRealEstateRemoteDataSource
import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.observer.IConnectivityObserver
import com.example.smsrly.domain.repository.IRealEstateRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealStateRepo @Inject constructor(
    private val realEstateRemoteDataSource: IRealEstateRemoteDataSource,
    private val realEstateLocalDataSource: IRealEstateLocalDataSource,
    private val connectionState: IConnectivityObserver
) : IRealEstateRepo {

    private val _allRealEstates = MutableStateFlow<Map<Int, RealEstate>>(emptyMap())
    private val _nearestRealEstates = MutableStateFlow<Map<Int, RealEstate>>(emptyMap())


    override suspend fun uploadARealState(realState: RealEstate): Result<Int> {
        return realEstateRemoteDataSource.uploadARealState(realState.toUploadDto())
    }

    override suspend fun uploadRealEstateImage(
        images: List<String>,
        id: Int
    ): Result<Int> {
        var sentImages = 0
        for (i in 0 until images.size) {
            val res = realEstateRemoteDataSource.uploadRealEstateImage(images[i], id)
            if (res.isSuccess) {
                sentImages++
            }
        }
        if (sentImages > 0) {
            return Result.success(sentImages)
        }
        return Result.failure(Exception("Failed to upload all images"))
    }


    override suspend fun getAllRealEstates(): Flow<List<RealEstate>> {
        getAllRealEstatesFromServer()
        return getAllRealEstatesFromDB()
    }

    suspend fun getAllRealEstatesFromServer(): Result<String> {
        val res = realEstateRemoteDataSource.getAllRealEstates()
        if (res.isSuccess) {
            val dtoList = res.getOrNull()!!.content
            //caching on fetching
            addRealEstatesToDB(dtoList.map {
                it.toDB()
            })
            val body = res.getOrNull()!!.toDomain()
            _allRealEstates.value = body.associateBy { it.id!! }
            return Result.success("Success")
        }
        return Result.failure(res.exceptionOrNull()!!)
    }

    fun getAllRealEstatesFromDB(): Flow<List<RealEstate>> {
        return realEstateLocalDataSource.getRealEstates().map {
            it.map {
                it.toDomain()
            }
        }
    }

    suspend fun addRealEstatesToDB(realEstates: List<RealEstateEntity>) {
        realEstateLocalDataSource.addRealEstates(realEstates)
    }

    override suspend fun getNeatestRealEstates(

    ): Flow<List<RealEstate>> {
        return realEstateLocalDataSource.getNearestRealEstates().map {
            it.map {
                it.toDomain()
            }
        }
    }


    override suspend fun saveARealEstate(id: Int): Result<String> {
        //update local first
        saveARealEstateInDb(id)
        //update server
        val res = realEstateRemoteDataSource.saveARealEstate(id)
        if (res.isSuccess) {
            return Result.success(res.getOrNull()?.message ?: "saved")
        } else {
            unSaveARealEstateInDb(id)
            return Result.failure(Exception(res.exceptionOrNull()?.message))
        }

    }

    suspend fun saveARealEstateInDb(id: Int) {
        realEstateLocalDataSource.saveARealEstate(id)
    }

    suspend fun unSaveARealEstateInDb(id: Int) {
        realEstateLocalDataSource.unSaveARealEstate(id)
    }

    override suspend fun unSaveARealEstate(id: Int): Result<String> {
        unSaveARealEstateInDb(id)
        val res = realEstateRemoteDataSource.unSaveARealEstate(id)
        if (res.isSuccess) {
            return Result.success(res.getOrNull()?.message ?: "unSaved")
        } else {
            saveARealEstateInDb(id)
            return Result.failure(Exception(res.exceptionOrNull()?.message))
        }

    }


    override fun getUserSavedRealEstates(): Flow<List<RealEstate>> {
        return realEstateLocalDataSource.getUserSaved().map {
            it.map {
                it.toDomain()
            }
        }
    }


    override suspend fun cancelRequest(id: Int): Result<String> {
       cancelRequestInDb(id)
        val res = realEstateRemoteDataSource.cancelRequest(id)
        if (res.isSuccess) {
            return Result.success(res.getOrNull()!!.message)
        }

        return Result.failure(Exception(res.exceptionOrNull()!!.message))
    }

    override fun getUserRequests(): Flow<List<RealEstate>> {
        return realEstateLocalDataSource.getUserRequests().map {
            it.map {
                it.toDomain()
            }
        }
    }

    override fun resetData() {
        _allRealEstates.value = emptyMap()
        _nearestRealEstates.value = emptyMap()
    }

    override suspend fun searchByTitle(title: String): Result<List<RealEstate>> {
        val res = realEstateRemoteDataSource.searchByTitle(title)
        if (res.isSuccess) {
            return Result.success(res.getOrNull()!!.toDomain())
        }
        return Result.failure(res.exceptionOrNull()!!)
    }

    override fun getRealEstateById(id: Int): Flow<RealEstate> {
        return realEstateLocalDataSource.getRealEstateById(id).map {
            it.toDomain()
        }
    }


    override suspend fun getUserUploads(): Result<List<RealEstate>> {
        val res = realEstateRemoteDataSource.getUserUploads()
        if (res.isSuccess) {
            return Result.success(res.getOrNull()!!.toDomain())
        } else {
            return Result.failure(res.exceptionOrNull()!!)
        }
    }

    override suspend fun sendRequest(id: Int): Result<String> {

        sendRequestInDb(id)
        //update server
        val res = realEstateRemoteDataSource.sendRequest(id)
        if (res.isSuccess) {
            Log.d("requested ","i successs")
            return Result.success("Request sent")
        } else {
            Log.d("requested ","i failed")
            cancelRequestInDb(id)
            val message = res.exceptionOrNull()?.message ?: "Failed to send request"
            return Result.failure(Exception(message))
        }
    }

    suspend fun sendRequestInDb(id:Int){
        realEstateLocalDataSource.sendRequest(id)
    }
    suspend fun cancelRequestInDb(id:Int){
        realEstateLocalDataSource.cancelRequest(id)
    }

}