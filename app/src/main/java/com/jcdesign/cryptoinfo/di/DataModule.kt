package com.jcdesign.cryptoinfo.di

import android.app.Application
import com.jcdesign.cryptoinfo.data.database.AppDataBase
import com.jcdesign.cryptoinfo.data.database.CoinInfoDao
import com.jcdesign.cryptoinfo.data.network.ApiFactory
import com.jcdesign.cryptoinfo.data.network.ApiService
import com.jcdesign.cryptoinfo.data.repository.CoinRepositoryImpl
import com.jcdesign.cryptoinfo.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(application: Application): CoinInfoDao {
            return AppDataBase.getInstance(application).coinPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService{
            return ApiFactory.apiService
        }
    }
}