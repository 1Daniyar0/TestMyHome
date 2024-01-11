package com.example.domain.usecase

import com.example.domain.models.Camera
import com.example.domain.repository.CamerasRepository

class UpdateCameraDbByIdUseCase(val camerasRepository: CamerasRepository) {
    suspend operator fun invoke(camera: Camera){
        camerasRepository.updateCamerasDataDb(camera)
    }
}