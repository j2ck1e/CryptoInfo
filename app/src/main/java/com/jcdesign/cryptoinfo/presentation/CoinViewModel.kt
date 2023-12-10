package com.jcdesign.cryptoinfo.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.jcdesign.cryptoinfo.data.network.ApiFactory
import com.jcdesign.cryptoinfo.data.database.AppDataBase
import com.jcdesign.cryptoinfo.data.model.CoinPriceInfo
import com.jcdesign.cryptoinfo.data.model.CoinPriceInfoRawData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application): AndroidViewModel(application) {

    private val db = AppDataBase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriceInfoDao().getPriceList() // объект LiveData

    init {
        loadData()
    }

    fun getDetailInfo(fSym: String): LiveData<CoinPriceInfo>{
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
    }

    private fun loadData(){
    val disposable = ApiFactory.apiService.getTopCoinsInfo()
        .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
        .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it) }
        .map { getPriceListFromRawData(it) }
        .delaySubscription(10, TimeUnit.SECONDS)
        .repeat()
        .retry()
        .subscribeOn(Schedulers.io())
        .subscribe({
            db.coinPriceInfoDao().insertPriceList(it)
        }, {
            Log.d("MyTag", it.message.toString())
        })
    compositeDisposable.add(disposable)
}

    private fun getPriceListFromRawData(
        coinPriceInfoRawData: CoinPriceInfoRawData
    ): List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}