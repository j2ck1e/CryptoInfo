package com.jcdesign.cryptoinfo.di

import android.app.Application
import com.jcdesign.cryptoinfo.data.database.AppDataBase
import com.jcdesign.cryptoinfo.data.database.CoinInfoDao
import com.jcdesign.cryptoinfo.data.repository.CoinRepositoryImpl
import com.jcdesign.cryptoinfo.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        fun provideCoinInfoDao(application: Application): CoinInfoDao {
            return AppDataBase.getInstance(application).coinPriceInfoDao()
        }
    }
}