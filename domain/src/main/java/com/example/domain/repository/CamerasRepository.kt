package com.example.domain.repository

import com.example.domain.models.Camera
import com.example.domain.models.CamerasRealmModel
import com.example.domain.models.CamerasResponse
import com.example.domain.models.DoorsRealmModel

interface CamerasRepository {
    suspend fun getCamerasApi(): CamerasResponse
    suspend fun fetchCamerasDataFromApi(data: List<Camera>): Boolean
    suspend fun updateCamerasDataDb(data: CamerasRealmModel)
    suspend fun getCamerasFromDb(): List<Camera>
    suspend fun getCamerasList(): List<Camera>?
}