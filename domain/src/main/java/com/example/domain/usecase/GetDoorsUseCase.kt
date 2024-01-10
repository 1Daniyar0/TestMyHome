package com.example.domain.usecase

import com.example.domain.models.DoorsResponse
import com.example.domain.repository.MyHomeRepository

class GetDoorsUseCase(private val repository: MyHomeRepository) {
    suspend operator fun invoke(): DoorsResponse {
        return repository.getDoors()
    }
}