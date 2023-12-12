package com.jcdesign.cryptoinfo.data.mapper

import com.google.gson.Gson
import com.jcdesign.cryptoinfo.data.database.CoinInfoDbModel
import com.jcdesign.cryptoinfo.data.network.model.CoinInfoDto
import com.jcdesign.cryptoinfo.data.network.model.CoinInfoJsonContainerDto
import com.jcdesign.cryptoinfo.data.network.model.CoinNamesListDto
import com.jcdesign.cryptoinfo.domain.CoinInfo
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class CoinMapper {
    fun mapDtoToDbModel(dto: CoinInfoDto) = CoinInfoDbModel(
        fromSymbol = dto.fromSymbol,
        toSymbol = dto.toSymbol,
        price = dto.price,
        lowDay = dto.lowDay,
        highDay = dto.highDay,
        lastMarket = dto.lastMarket,
        lastUpdate = dto.lastUpdate,
        imageUrl = BASE_IMAGE_URL + dto.imageUrl
    )

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNamesListToString(namesListDto: CoinNamesListDto): String {
        return namesListDto.names?.map {
            it.coinNameDto?.name
        }?.joinToString(",") ?: ""
    }

    fun mapDbModelToEntity(dbModel: CoinInfoDbModel) = CoinInfo(
        fromSymbol = dbModel.fromSymbol,
        toSymbol = dbModel.toSymbol,
        price = dbModel.price,
        lowDay = dbModel.lowDay,
        highDay = dbModel.highDay,
        lastMarket = dbModel.lastMarket,
        lastUpdate = convertTimestampToTime(dbModel.lastUpdate),
        imageUrl = dbModel.imageUrl
    )

    private fun convertTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss" // HH = 24 hours
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    companion object{
        const val BASE_IMAGE_URL = "https://cryptocompare.com"
    }
}
