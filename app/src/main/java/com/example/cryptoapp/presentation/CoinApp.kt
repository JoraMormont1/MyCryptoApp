package com.example.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoapp.data.workers.CoinWorkerFactory
import com.example.cryptoapp.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    @Inject
    lateinit var workerFactory: CoinWorkerFactory

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                workerFactory
            )
            .build()
    }
}
