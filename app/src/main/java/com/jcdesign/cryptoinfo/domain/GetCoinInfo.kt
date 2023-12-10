package com.jcdesign.cryptoinfo.domain

class GetCoinInfo(private val repository: CoinRepository) {

    operator fun invoke(fromSymbol: String) = repository.getCoinInfo(fromSymbol)
}