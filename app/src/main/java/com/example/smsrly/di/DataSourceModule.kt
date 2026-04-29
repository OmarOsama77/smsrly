package com.example.smsrly.di

import com.example.smsrly.data.local.datasource.realestatelocaldatasource.IRealEstateLocalDataSource
import com.example.smsrly.data.local.datasource.realestatelocaldatasource.RealEstateLocalDataSource
import com.example.smsrly.data.cache.datasource.tokendatasource.ITokenLocalDataSource
import com.example.smsrly.data.cache.datasource.tokendatasource.TokenLocalDataSource
import com.example.smsrly.data.remote.datasource.authdatasource.AuthDataSourceImp
import com.example.smsrly.data.remote.datasource.authdatasource.IAuthDataSource
import com.example.smsrly.data.remote.datasource.firebasedatasource.FirebaseDataSource
import com.example.smsrly.data.remote.datasource.firebasedatasource.IFirebaseDataSource
import com.example.smsrly.data.remote.datasource.realestateremotedatasource.IRealEstateRemoteDataSource
import com.example.smsrly.data.remote.datasource.realestateremotedatasource.RealEstateDataRemoteSourceImp
import com.example.smsrly.data.remote.datasource.userdatasource.IUserDataSource
import com.example.smsrly.data.remote.datasource.userdatasource.UserDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {


    @Binds
    @Singleton
    abstract fun bindAuthDataSource(authDataSource: AuthDataSourceImp) : IAuthDataSource

    @Binds
    @Singleton
    abstract fun bindTokenDataSource(tokenLocalDataSource: TokenLocalDataSource) : ITokenLocalDataSource


    @Binds
    @Singleton
    abstract fun bindRealEstateDataSource(realEstateDataSourceImp: RealEstateDataRemoteSourceImp) : IRealEstateRemoteDataSource


    @Binds
    @Singleton
    abstract fun bindUserDataSource(userDataSource: UserDataSource): IUserDataSource

    @Binds
    @Singleton
    abstract fun bindFirebaseDataSource(firebaseDataSource: FirebaseDataSource): IFirebaseDataSource
    @Binds
    @Singleton
    abstract fun bindRealEstateLocalDataSource(realEstateLocalDataSource: RealEstateLocalDataSource): IRealEstateLocalDataSource

}