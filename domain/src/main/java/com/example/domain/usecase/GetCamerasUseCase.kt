package com.example.domain.usecase

import com.example.domain.models.CamerasResponse
import com.example.domain.repository.MyHomeRepository

class GetCamerasUseCase(private val repository: MyHomeRepository) {
    suspend operator fun invoke():CamerasResponse{
           return repository.getCameras()
    }
}