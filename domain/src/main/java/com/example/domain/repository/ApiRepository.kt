package com.example.domain.repository

import com.example.domain.models.CamerasResponse
import com.example.domain.models.DoorsResponse

interface ApiRepository {
    suspend fun getCameras(): CamerasResponse

    suspend fun getDoors(): DoorsResponse
}