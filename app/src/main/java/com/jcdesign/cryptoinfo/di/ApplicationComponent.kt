package com.jcdesign.cryptoinfo.di

import android.app.Application
import com.jcdesign.cryptoinfo.presentation.CoinDetailFragment
import com.jcdesign.cryptoinfo.presentation.CoinPriceListActivity
import com.jcdesign.cryptoinfo.presentation.ViewModelFactory
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}