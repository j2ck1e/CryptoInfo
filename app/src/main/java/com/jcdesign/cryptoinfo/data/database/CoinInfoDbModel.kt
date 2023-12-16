package com.jcdesign.cryptoinfo.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("full_price_list")
data class CoinInfoDbModel(
    val price: String?,
    val lowDay: String?,
    val highDay: String?,
    val lastMarket: String?,
    val lastUpdate: Long?,
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String?,
    val imageUrl: String
)