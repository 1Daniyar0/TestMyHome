package com.example.domain.usecase

import com.example.domain.models.DoorsResponse
import com.example.domain.repository.ApiRepository

class GetDoorsUseCase(private val repository: ApiRepository) {
    suspend operator fun invoke(): DoorsResponse {
        return repository.getDoors()
    }
}