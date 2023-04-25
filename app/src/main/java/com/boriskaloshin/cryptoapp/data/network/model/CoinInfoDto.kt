package com.boriskaloshin.cryptoapp.data.network.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CoinInfoDto(
    @SerializedName("TYPE")
    val type: String?,

    @SerializedName("MARKET")
    val market: String?,

    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    val fromSymbol: String,

    @SerializedName("TOSYMBOL")
    val toSymbol: String?,

    @SerializedName("FLAGS")
    val flags: String?,

    @SerializedName("PRICE")
    val price: Double?,

    @SerializedName("LASTUPDATE")
    val lastUpdate: Long?,

    @SerializedName("LASTVOLUME")
    val lastVolume: String?,

    @SerializedName("OPENDAY")
    val openDay: Double?,

    @SerializedName("HIGHDAY")
    val highDay: Double?,

    @SerializedName("LOWDAY")
    val lowDay: Double?,

    @SerializedName("LASTMARKET")
    val lastMarket: String?,

    @SerializedName("OPENHOUR")
    val openHour: Double?,

    @SerializedName("HIGHHOUR")
    val highHourval: Double?,

    @SerializedName("LOWHOUR")
    val lowHour: Double?,

    @SerializedName("IMAGEURL")
    val imageUrl: String?
)