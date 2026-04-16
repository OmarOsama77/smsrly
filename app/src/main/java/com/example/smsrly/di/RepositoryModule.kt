package com.example.smsrly.di

import com.example.smsrly.data.repository.AuthRepo
import com.example.smsrly.data.repository.FirebaseRepo
import com.example.smsrly.data.repository.RealStateRepo
import com.example.smsrly.data.repository.TokenRepo
import com.example.smsrly.data.repository.UserRepo
import com.example.smsrly.domain.repository.IAuthRepo
import com.example.smsrly.domain.repository.IFirebaseRepo
import com.example.smsrly.domain.repository.IRealEstateRepo
import com.example.smsrly.domain.repository.ITokenRepo
import com.example.smsrly.domain.repository.IUserRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepo(authRepo: AuthRepo): IAuthRepo

    @Binds
    @Singleton
    abstract fun bindTokenRepo(tokenRepo: TokenRepo): ITokenRepo


    @Binds
    @Singleton
    abstract fun bindUserRepo(userRepo: UserRepo): IUserRepo

    @Binds
    @Singleton
    abstract fun bindRealStateRepo(realState: RealStateRepo): IRealEstateRepo

    @Binds
    @Singleton
    abstract fun bindFirebaseRepo(firebaseRepo: FirebaseRepo): IFirebaseRepo

}


