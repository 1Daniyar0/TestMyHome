package com.example.testmyhome

import android.app.Application
import com.example.testmyhome.di.appModule
import com.example.testmyhome.di.netModule
import com.example.testmyhome.di.viewModelModule
import io.ktor.http.auth.HttpAuthHeader.Parameters.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

open class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{

            androidContext(this@App)
            modules(appModule,netModule, viewModelModule)

        }

    }

}