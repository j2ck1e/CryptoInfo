package com.jcdesign.cryptoinfo.presentation

import android.app.Application
import androidx.work.Configuration
import com.jcdesign.cryptoinfo.di.DaggerApplicationComponent
import com.jcdesign.cryptoinfo.workers.CoinWorkerFactory
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CoinWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}