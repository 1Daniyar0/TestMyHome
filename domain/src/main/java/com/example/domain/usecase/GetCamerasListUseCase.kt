package com.example.domain.usecase

import com.example.domain.models.Camera
import com.example.domain.repository.CamerasRepository

class GetCamerasListUseCase(private val camerasRepository: CamerasRepository) {
    suspend operator fun invoke():List<Camera>?{
           return camerasRepository.getCamerasList()
    }
}