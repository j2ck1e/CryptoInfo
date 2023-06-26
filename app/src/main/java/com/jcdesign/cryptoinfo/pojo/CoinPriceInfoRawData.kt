package com.jcdesign.cryptoinfo.pojo

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CoinPriceInfoRawData(
    @SerializedName(/* value = */ "RAW")
    @Expose
    val coinPriceInfoJsonObject: JsonObject? = null
)
