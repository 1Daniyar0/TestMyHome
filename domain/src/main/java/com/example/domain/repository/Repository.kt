package com.example.domain.repository

import com.example.domain.models.Camera
import com.example.domain.models.CamerasResponse
import com.example.domain.models.DoorsRealmModel
import com.example.domain.models.DoorsResponse

interface Repository {
    suspend fun getCamerasApi(): CamerasResponse
    suspend fun getDoorsApi(): DoorsResponse
    suspend fun fetchCamerasDataFromApi(data: List<Camera>): Boolean
    suspend fun updateDataInDb(data: DoorsRealmModel)
    suspend fun getCamerasFromDb(): List<Camera>
    suspend fun getCamerasList(): List<Camera>?
}