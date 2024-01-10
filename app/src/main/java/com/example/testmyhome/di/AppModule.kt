package com.example.testmyhome.di

import com.example.data.realm.CamerasRealmModel
import com.example.data.remote.ApiClient.client
import com.example.data.remote.MyHomeRepositoryImpl
import com.example.domain.repository.MyHomeRepository
import com.example.domain.usecase.GetCamerasUseCase
import com.example.domain.usecase.GetDoorsUseCase
import com.example.testmyhome.screens.MyHomeViewModel
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
    factory { GetCamerasUseCase(get()) }
    factory { GetDoorsUseCase(get()) }
    single<MyHomeRepository>{ MyHomeRepositoryImpl() }
}

val viewModelModule = module {
    viewModel {MyHomeViewModel(get(),get())}
}

val netModule = module{
    factory {client}
}

val realmModule = module {
    val config = RealmConfiguration.create(schema = setOf(CamerasRealmModel::class))
    val realm: Realm = Realm.open(config)
}





