package com.example.domain.repository

import com.example.domain.models.Camera
import com.example.domain.models.CamerasDataBaseModel
import com.example.domain.models.CamerasResponse

interface CamerasRepository {
    suspend fun getCamerasApi(): CamerasResponse
    suspend fun fetchCamerasDataFromApi(data: List<Camera>): Boolean
    suspend fun updateCamerasDataDb(camera: Camera)
    suspend fun getCamerasFromDb(): List<Camera>
    suspend fun getCamerasList(): List<Camera>?
}