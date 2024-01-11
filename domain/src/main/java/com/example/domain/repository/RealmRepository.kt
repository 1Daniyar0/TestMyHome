package com.example.domain.repository

import com.example.domain.models.Camera
import com.example.domain.models.CamerasRealmModel
import com.example.domain.models.DoorsRealmModel

interface RealmRepository {
    suspend fun addCamerasToRealm(data: List<Camera>): Boolean
    suspend fun updateDataInRealm(data: DoorsRealmModel)
    suspend fun getCamersFromDb(data: DoorsRealmModel): List<CamerasRealmModel>
}