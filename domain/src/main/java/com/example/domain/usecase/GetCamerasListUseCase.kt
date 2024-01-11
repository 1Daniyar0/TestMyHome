package com.example.domain.usecase

import com.example.domain.models.Camera
import com.example.domain.models.CamerasResponse
import com.example.domain.repository.Repository

class GetCamerasListUseCase(private val repository: Repository) {
    suspend operator fun invoke():List<Camera>?{
           return repository.getCamerasList()
    }
}