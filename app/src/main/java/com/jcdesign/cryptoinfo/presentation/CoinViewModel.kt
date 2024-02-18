package com.jcdesign.cryptoinfo.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcdesign.cryptoinfo.data.repository.CoinRepositoryImpl
import com.jcdesign.cryptoinfo.domain.GetCoinInfoListUseCase
import com.jcdesign.cryptoinfo.domain.GetCoinInfoUseCase
import com.jcdesign.cryptoinfo.domain.LoadDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    loadDataUseCase: LoadDataUseCase,
) : ViewModel() {


    val coinInfoList = getCoinInfoListUseCase()

    init {
        loadDataUseCase()
    }

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)
}