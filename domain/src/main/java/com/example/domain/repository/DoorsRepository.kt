package com.example.domain.repository

import com.example.domain.models.Camera
import com.example.domain.models.CamerasResponse
import com.example.domain.models.Door
import com.example.domain.models.DoorsRealmModel
import com.example.domain.models.DoorsResponse

interface DoorsRepository {
    suspend fun getDoorsApi(): DoorsResponse
    suspend fun fetchDoorsDataFromApi(data: List<Door>): Boolean
    suspend fun updateDoorsDataInDb(data: DoorsRealmModel)
    suspend fun getDoorsFromDb(): List<Door>
    suspend fun getDoorsList(): List<Door>?
}