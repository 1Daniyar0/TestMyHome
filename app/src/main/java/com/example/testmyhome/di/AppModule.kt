package com.example.testmyhome.di

import com.example.data.remote.ApiClient.client
import com.example.data.repositoryImpl.RepositoryImpl
import com.example.domain.repository.Repository
import com.example.domain.usecase.GetCamerasListUseCase
import com.example.domain.usecase.GetDoorsUseCase
import com.example.testmyhome.screens.MyHomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
    factory { GetCamerasListUseCase(get()) }
    factory { GetDoorsUseCase(get()) }
    single<Repository> { RepositoryImpl() }
}

val viewModelModule = module {
    viewModel {MyHomeViewModel(get(),get())}
}

val netModule = module{
    factory {client}
}






