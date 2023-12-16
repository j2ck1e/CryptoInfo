package com.jcdesign.cryptoinfo.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jcdesign.cryptoinfo.data.repository.CoinRepositoryImpl
import com.jcdesign.cryptoinfo.domain.GetCoinInfoListUseCase
import com.jcdesign.cryptoinfo.domain.GetCoinInfoUseCase
import com.jcdesign.cryptoinfo.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)


    val coinInfoList = getCoinInfoListUseCase()

    init {
        loadDataUseCase()
    }

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)
}