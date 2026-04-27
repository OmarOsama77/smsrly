package com.example.smsrly.data.remote.datasource.realestateremotedatasource

import com.example.smsrly.data.remote.dto.realestate.getrealestates.GetRealEstatesDto
import com.example.smsrly.data.remote.dto.realestate.getuseruploads.GetUserUploadsDto
import com.example.smsrly.data.remote.dto.realestate.savearealestate.SaveARealEstateDto
import com.example.smsrly.data.remote.dto.realestate.sendRequest.SuccessfulRequestDto
import com.example.smsrly.data.remote.dto.realestate.uploadRealState.UploadRealStateDto

interface IRealEstateRemoteDataSource{
    suspend fun uploadARealState(realStateDto: UploadRealStateDto):Result<Int>
    suspend fun uploadRealEstateImage(image: String,id:Int) :Result<String>
    suspend fun getAllRealEstates():Result<GetRealEstatesDto>
    suspend fun getNearestRealEstates():Result<GetRealEstatesDto>
    suspend fun getUserUploads():Result<GetUserUploadsDto>
    suspend fun sendRequest(id:Int): Result<SuccessfulRequestDto>
    suspend fun saveARealEstate(id:Int):Result<SaveARealEstateDto>
    suspend fun unSaveARealEstate(id:Int):Result<SaveARealEstateDto>
    suspend fun getUserSavedRealEstates():Result<GetRealEstatesDto>
    suspend fun cancelRequest(id:Int):Result<SuccessfulRequestDto>
    suspend fun getUserRequests():Result<GetRealEstatesDto>
    suspend fun searchByTitle(title:String): Result<GetRealEstatesDto>

}