package com.jcdesign.cryptoinfo.presentation

import android.app.Application
import com.jcdesign.cryptoinfo.di.DaggerApplicationComponent

class CoinApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}