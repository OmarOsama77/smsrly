package com.example.smsrly.data.repository

import android.util.Log
import com.example.smsrly.data.local.datasource.realestatelocaldatasource.IRealEstateLocalDataSource
import com.example.smsrly.data.local.db.entities.RealEstateEntity
import com.example.smsrly.data.mapper.toDB
import com.example.smsrly.data.mapper.toDomain
import com.example.smsrly.data.mapper.toUploadDto
import com.example.smsrly.data.remote.datasource.realestateremotedatasource.IRealEstateRemoteDataSource
import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.repository.IRealEstateRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealStateRepo @Inject constructor(
    private val realEstateDataSource: IRealEstateRemoteDataSource,
    private val realEstateLocalDataSource: IRealEstateLocalDataSource
) : IRealEstateRepo {

    private val _allRealEstates = MutableStateFlow<Map<Int, RealEstate>>(emptyMap())
    private val _nearestRealEstates = MutableStateFlow<Map<Int, RealEstate>>(emptyMap())


    override suspend fun uploadARealState(realState: RealEstate): Result<Int> {
        return realEstateDataSource.uploadARealState(realState.toUploadDto())
    }

    override suspend fun uploadRealEstateImage(
        images: List<String>,
        id: Int
    ): Result<Int> {
        var sentImages = 0
        for (i in 0 until images.size) {
            val res = realEstateDataSource.uploadRealEstateImage(images[i], id)
            if (res.isSuccess) {
                sentImages++
            }
        }
        if (sentImages > 0) {
            return Result.success(sentImages)
        }
        return Result.failure(Exception("Failed to upload all images"))
    }


    override suspend fun getAllRealEstates(): Result<String> {
        val res = realEstateDataSource.getAllRealEstates()
        if (res.isSuccess) {
           val dtoList = res.getOrNull()?.content

            addRealEstatesToDB(dtoList!!.map {
                it.toDB()
            })
            val body = res.getOrNull()!!.toDomain()
            _allRealEstates.value = body.associateBy { it.id!! }
            return Result.success("Success")
        }
        return Result.failure(res.exceptionOrNull()!!)
    }

    suspend fun addRealEstatesToDB(realEstates:List<RealEstateEntity>) {
        realEstateLocalDataSource.addRealEstates(realEstates)
    }
    override suspend fun getNeatestRealEstates(): Result<String> {
        val res = realEstateDataSource.getNearestRealEstates()
        if (res.isSuccess) {
            val body = res.getOrNull()!!.toDomain()
            val oldMap = _allRealEstates.value
            _allRealEstates.value = oldMap + body.associateBy { it.id!! }
            _nearestRealEstates.value = body.associateBy { it.id!! }
            return Result.success("Success")
        }
        return Result.failure(res.exceptionOrNull()!!)
    }

    override fun getNearestRealEstateObj(): StateFlow<Map<Int, RealEstate>> {
        return _nearestRealEstates
    }

    override suspend fun saveARealEstate(id: Int): Result<String> {
        toggleSaved(id, true)
        val res = realEstateDataSource.saveARealEstate(id)

        if (res.isSuccess) {
            return Result.success(res.getOrNull()!!.message)
        }
        toggleSaved(id, false)
        return Result.failure(Exception(res.exceptionOrNull()!!.message))
    }

    override suspend fun unSaveARealEstate(id: Int): Result<String> {
        toggleSaved(id, false)
        val res = realEstateDataSource.unSaveARealEstate(id)
        if (res.isSuccess) {
            return Result.success("Success")
        }
        toggleSaved(id, true)
        return Result.failure(Exception(res.exceptionOrNull()!!.message))
    }


    override fun getUserSavedRealEstates(): Flow<List<RealEstate>> {
        return _allRealEstates.map {
            it.values.filter { it.isSaved!! }
        }
    }


    override fun toggleSaved(id: Int, isSaved: Boolean) {
        if (isSaved) {
            //made it to be saved
            val current = _allRealEstates.value.toMutableMap()
            current[id] = current[id]!!.copy(isSaved = true)
            _allRealEstates.value = current
            if (_nearestRealEstates.value.contains(id)) {
                val updated = _nearestRealEstates.value.toMutableMap()
                updated[id] = updated[id]!!.copy(isSaved = true)
                _nearestRealEstates.value = updated
            }
        } else {
            //made it to be unsaved
            val current = _allRealEstates.value.toMutableMap()
            current[id] = current[id]!!.copy(isSaved = false)
            _allRealEstates.value = current
            if (_nearestRealEstates.value.contains(id)) {
                val updated = _nearestRealEstates.value.toMutableMap()
                updated[id] = updated[id]!!.copy(isSaved = false)
                _nearestRealEstates.value = updated
            }
        }
    }

    override suspend fun cancelRequest(id: Int): Result<String> {
        toggleRequested(id, false)
        val res = realEstateDataSource.cancelRequest(id)
        if (res.isSuccess) {
            return Result.success(res.getOrNull()!!.message)
        }
        toggleRequested(id, true)
        return Result.failure(Exception(res.exceptionOrNull()!!.message))
    }

    override fun getUserRequests(): Flow<List<RealEstate>> {
        return _allRealEstates.map {
            it.values.filter {
                it.isRequested!!
            }
        }
    }

    override fun resetData() {
        _allRealEstates.value = emptyMap()
        _nearestRealEstates.value = emptyMap()
    }

    override suspend fun searchByTitle(title: String): Result<List<RealEstate>> {
        val res = realEstateDataSource.searchByTitle(title)
        if (res.isSuccess) {
            return Result.success(res.getOrNull()!!.toDomain())
        }
        return Result.failure(res.exceptionOrNull()!!)
    }




    override suspend fun getUserUploads(): Result<List<RealEstate>> {
        val res = realEstateDataSource.getUserUploads()
        if (res.isSuccess) {
            return Result.success(res.getOrNull()!!.toDomain())
        } else {
            return Result.failure(res.exceptionOrNull()!!)
        }
    }

    override suspend fun sendRequest(id: Int): Result<String> {
        toggleRequested(id, true)
        val res = realEstateDataSource.sendRequest(id)
        if (res.isSuccess) {
            return Result.success("Request sent")
        } else {
            toggleRequested(id, false)
            val message = res.exceptionOrNull()?.message ?: "Failed to send request"
            return Result.failure(Exception(message))
        }
    }

    override fun toggleRequested(id: Int, isRequested: Boolean) {
        if (isRequested) {
            val allRealEstate = _allRealEstates.value.toMutableMap()
            allRealEstate[id] = allRealEstate[id]!!.copy(isRequested = true)
            _allRealEstates.value = allRealEstate
            if (_nearestRealEstates.value.contains(id)) {
                val updated = _nearestRealEstates.value.toMutableMap()
                updated[id] = updated[id]!!.copy(isRequested = true)
                _nearestRealEstates.value = updated
            }
        } else {
            val allRealEstate = _allRealEstates.value.toMutableMap()
            allRealEstate[id] = allRealEstate[id]!!.copy(isRequested = false)
            _allRealEstates.value = allRealEstate
            if (_nearestRealEstates.value.contains(id)) {
                val updated = _nearestRealEstates.value.toMutableMap()
                updated[id] = updated[id]!!.copy(isRequested = false)
                _nearestRealEstates.value = updated
            }
        }
    }

    override fun getRealEstateObj(): StateFlow<Map<Int, RealEstate>> {
        return _allRealEstates
    }


}