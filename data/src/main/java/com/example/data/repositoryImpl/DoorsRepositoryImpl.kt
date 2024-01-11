package com.example.data.repositoryImpl

import com.example.data.remote.ApiClient
import com.example.data.remote.ApiRoutes
import com.example.domain.models.Door
import com.example.domain.models.DoorsRealmModel
import com.example.domain.models.DoorsResponse
import com.example.domain.repository.DoorsRepository
import io.ktor.client.call.body
import io.ktor.client.request.get

class DoorsRepositoryImpl:DoorsRepository {
    override suspend fun getDoorsApi(): DoorsResponse = ApiClient.client.get(ApiRoutes.DOORS_GET).body()

    override suspend fun fetchDoorsDataFromApi(data: List<Door>): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateDoorsDataInDb(data: DoorsRealmModel) {
        TODO("Not yet implemented")
    }

    override suspend fun getDoorsFromDb(): List<Door> {
        TODO("Not yet implemented")
    }

    override suspend fun getDoorsList(): List<Door>? {
        TODO("Not yet implemented")
    }
}