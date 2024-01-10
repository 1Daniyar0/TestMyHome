package com.example.data.remote

import com.example.data.remote.ApiClient.client
import com.example.domain.models.CamerasResponse
import com.example.domain.models.DoorsResponse
import com.example.domain.repository.MyHomeRepository
import io.ktor.client.call.body
import io.ktor.client.request.get

class MyHomeRepositoryImpl: MyHomeRepository {
    override suspend fun getCameras():CamerasResponse = client.get(ApiRoutes.CAMERAS_GET).body()

    override suspend fun getDoors(): DoorsResponse = client.get(ApiRoutes.DOORS_GET).body()
}