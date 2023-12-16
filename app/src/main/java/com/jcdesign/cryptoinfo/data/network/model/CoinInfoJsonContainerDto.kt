package com.jcdesign.cryptoinfo.data.network.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CoinInfoJsonContainerDto(
    @SerializedName(/* value = */ "RAW")
    @Expose
    val json: JsonObject? = null
)
