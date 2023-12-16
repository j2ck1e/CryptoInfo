package com.jcdesign.cryptoinfo.data.network

import com.jcdesign.cryptoinfo.data.network.model.CoinNamesListDto
import com.jcdesign.cryptoinfo.data.network.model.CoinInfoJsonContainerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/totalvolfull")
    suspend fun getTopCoinsInfo(
        @Query(QUERY_API_KEY) apiKey: String = MY_API_KEY,
        @Query(QUERY_PARAM_LIMIT) limit: Int = 50,
        @Query(QUERY_PARAM_TO_SYMBOL) tsym: String = CURRENCY
    ) : CoinNamesListDto

    @GET("pricemultifull")
    suspend fun getFullPriceList(
        @Query(QUERY_API_KEY) apiKey: String = MY_API_KEY,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY

    ) : CoinInfoJsonContainerDto

    companion object{
        private const val MY_API_KEY = "a4a11f52fabe4d4059a2b69a154f2d348b20cb2b5525201cd86103273611d60b"

        private const val QUERY_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"

        private const val CURRENCY = "USD"
    }
}