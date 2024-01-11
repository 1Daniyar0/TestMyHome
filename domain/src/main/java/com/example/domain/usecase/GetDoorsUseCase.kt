package com.example.domain.usecase

import com.example.domain.models.DoorsResponse
import com.example.domain.repository.CamerasRepository
import com.example.domain.repository.DoorsRepository

class GetDoorsUseCase(private val doorsRepository: DoorsRepository) {
    suspend operator fun invoke(): DoorsResponse {
        return doorsRepository.getDoorsApi()
    }
}