package com.example.smsrly.presentation.ui.screens.myadds.screens.uploaded.state

import com.example.smsrly.domain.models.RealEstate

sealed class UploadsState{
    object Idle : UploadsState()
    object Loading: UploadsState()
    data class Success(
        val data : List<RealEstate>
    ) : UploadsState()
    data class Failure(
        val message:String
    ): UploadsState()
}