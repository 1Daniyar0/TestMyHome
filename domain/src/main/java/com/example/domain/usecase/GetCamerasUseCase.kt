package com.example.domain.usecase

import com.example.domain.models.CamerasResponse
import com.example.domain.repository.ApiRepository

class GetCamerasUseCase(private val repository: ApiRepository) {
    suspend operator fun invoke():CamerasResponse{
           return repository.getCameras()
    }
}