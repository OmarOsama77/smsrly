package com.example.smsrly.data.remote.apiservice

import com.example.smsrly.data.remote.dto.login.LoginRequestDto
import com.example.smsrly.data.remote.dto.login.LoginSuccessDto
import com.example.smsrly.data.remote.dto.otp.OtpRequestDto
import com.example.smsrly.data.remote.dto.otp.OtpSuccessDto
import com.example.smsrly.data.remote.dto.realestate.getrealestates.GetRealEstatesDto
import com.example.smsrly.data.remote.dto.realestate.getuseruploads.GetUserUploadsDto
import com.example.smsrly.data.remote.dto.realestate.savearealestate.SaveARealEstateDto
import com.example.smsrly.data.remote.dto.realestate.searchrealestate.SearchDto
import com.example.smsrly.data.remote.dto.realestate.sendRequest.SuccessfulRequestDto
import com.example.smsrly.data.remote.dto.realestate.uploadRealState.SuccessUploadRealState
import com.example.smsrly.data.remote.dto.realestate.uploadRealState.UploadRealStateDto
import com.example.smsrly.data.remote.dto.realestate.uploadRealState.uploadrealestateimage.UploadRealEstateImageSuccessDto
import com.example.smsrly.data.remote.dto.signup.SignupRequestDto
import com.example.smsrly.data.remote.dto.signup.SignupSuccessDto
import com.example.smsrly.data.remote.dto.signup.userimage.UserImageSuccessDto
import com.example.smsrly.data.remote.dto.tokens.RefreshTokenDtoSuccess
import com.example.smsrly.data.remote.dto.user.DeleteUserDto
import com.example.smsrly.data.remote.dto.user.GetUserResponse
import com.example.smsrly.data.remote.dto.user.GetUsersDataDtoRequest
import com.example.smsrly.data.remote.dto.user.UserInfoDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @POST(ApiConstants.send_otp)
    suspend fun sendOtp(
        @Body request: OtpRequestDto
    ): Response<OtpSuccessDto>

    @POST(ApiConstants.signup)
    suspend fun signup(
        @Query("otp") otp: String,
        @Body request: SignupRequestDto
    ): Response<SignupSuccessDto>

    @Multipart
    @POST(ApiConstants.userImage)
    suspend fun uploadUserImage(
        @Header("Authorization") accessToken: String,
        @Part request: MultipartBody.Part
    ): Response<UserImageSuccessDto>

    @POST(ApiConstants.login)
    suspend fun login(
        @Body request: LoginRequestDto
    ): Response<LoginSuccessDto>

    @GET(ApiConstants.userData)
    suspend fun getUserData(
    ): Response<GetUserResponse>

    @POST(ApiConstants.uploadRealState)
    suspend fun uploadRealState(
        @Body request: UploadRealStateDto
    ): Response<SuccessUploadRealState>

    @POST(ApiConstants.refreshToken)
    suspend fun refreshAccessToken(
        @Header("Authorization") refreshToken: String
    ): Response<RefreshTokenDtoSuccess>


    @Multipart
    @POST("${ApiConstants.uploadRealEstateImage}/{id}")
    suspend fun uploadRealEstateImage(
        @Part request: MultipartBody.Part,
        @Path("id") realEstateId: Int,
    ): Response<UploadRealEstateImageSuccessDto>

    @GET(ApiConstants.getAllRealEstates)
    suspend fun getAllRealEstates(
    ): Response<GetRealEstatesDto>

    @GET(ApiConstants.getNearestRealEstates)
    suspend fun getNearestRealEstates(

    ): Response<GetRealEstatesDto>


    @GET(ApiConstants.getUserUploads)
    suspend fun getUserUploads(): Response<GetUserUploadsDto>


    @POST(ApiConstants.sendRequest)
    suspend fun sendRequest(
        @Path("id") id: Int
    ): Response<SuccessfulRequestDto>

    @DELETE(ApiConstants.sendRequest)
    suspend fun deleteRequest(
        @Path("id") id: Int
    ): Response<SuccessfulRequestDto>
    @GET(ApiConstants.getUserSavedRealEstate)
    suspend fun getUserSavedRealEstates():Response<GetRealEstatesDto>
    @POST(ApiConstants.saveARealEstate)
    suspend fun saveARealEstate(
        @Path("id") id:Int
    ):Response<SaveARealEstateDto>


    @DELETE(ApiConstants.saveARealEstate)
    suspend fun unSaveARealEstate(
        @Path("id") id:Int
    ):Response<SaveARealEstateDto>

    @GET (ApiConstants.getUserRequests)
    suspend fun getUserRequests():Response<GetRealEstatesDto>

    @POST(ApiConstants.searchByTitle)
    suspend fun searchByTitle(
        @Body request: SearchDto
    ):Response<GetRealEstatesDto>
    @DELETE(ApiConstants.deleteUser)
    suspend fun deleteUser(): Response<DeleteUserDto>

    @POST(ApiConstants.getUsersDataByIds)
    suspend fun getUsersDataByIds(
        @Body request : GetUsersDataDtoRequest
    ):Response<Map<String,UserInfoDto>>
}


