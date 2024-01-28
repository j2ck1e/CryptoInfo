package com.jcdesign.cryptoinfo.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.jcdesign.cryptoinfo.data.database.AppDataBase
import com.jcdesign.cryptoinfo.data.database.CoinInfoDao
import com.jcdesign.cryptoinfo.data.mapper.CoinMapper
import com.jcdesign.cryptoinfo.data.network.ApiFactory
import com.jcdesign.cryptoinfo.domain.CoinInfo
import com.jcdesign.cryptoinfo.domain.CoinRepository
import com.jcdesign.cryptoinfo.workers.RefreshDataWorker
import kotlinx.coroutines.delay
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val mapper : CoinMapper,
    private val coinInfoDao: CoinInfoDao,
    private val application: Application
) : CoinRepository {


    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        val coinInfoList = coinInfoDao.getPriceList()
        return coinInfoList.map {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        val coinInfo = coinInfoDao.getPriceInfoAboutCoin(fromSymbol)
        return coinInfo.map {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.workRequest()
        )
    }
}
