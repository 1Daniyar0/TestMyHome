package com.example.domain.usecase

import com.example.domain.models.Camera
import com.example.domain.models.Door
import com.example.domain.models.DoorsResponse
import com.example.domain.repository.CamerasRepository
import com.example.domain.repository.DoorsRepository

class UpdateDoorDbByIdUseCase(val doorsRepository: DoorsRepository) {
    suspend operator fun invoke(door: Door){
        doorsRepository.updateDoorsDataInDb(door)
    }
}