package com.example.domain.repository

import com.example.domain.models.Door
import com.example.domain.models.DoorsDataBaseModel
import com.example.domain.models.DoorsResponse

interface DoorsRepository {
    suspend fun getDoorsApi(): DoorsResponse
    suspend fun fetchDoorsDataFromApi(data: List<Door>): Boolean
    suspend fun updateDoorsDataInDb(data: DoorsDataBaseModel)
    suspend fun getDoorsFromDb(): List<Door>
    suspend fun getDoorsList(): List<Door>?
}