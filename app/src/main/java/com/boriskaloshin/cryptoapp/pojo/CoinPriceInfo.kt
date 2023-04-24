package com.boriskaloshin.cryptoapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.boriskaloshin.cryptoapp.api.ApiFactory
import com.boriskaloshin.cryptoapp.api.ApiFactory.BASE_IMAGE_URL
import com.boriskaloshin.cryptoapp.utils.convertTimestampToTime
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.annotations.NonNull

@Entity (tableName = "full_price_list")
data class CoinPriceInfo(
    @SerializedName("TYPE")
    @Expose
    val type: String?,

    @SerializedName("MARKET")
    @Expose
    val market: String?,

    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    @Expose
    val fromSymbol: String,

    @SerializedName("TOSYMBOL")
    @Expose
    val toSymbol: String?,

    @SerializedName("FLAGS")
    @Expose
    val flags: String?,

    @SerializedName("PRICE")
    @Expose
    val price: Double?,

    @SerializedName("LASTUPDATE")
    @Expose
    val lastUpdate: Long?,

    @SerializedName("LASTVOLUME")
    @Expose
    val lastVolume: String?,

    @SerializedName("OPENDAY")
    @Expose
    val openDay: Double?,

    @SerializedName("HIGHDAY")
    @Expose
    val highDay: Double?,

    @SerializedName("LOWDAY")
    @Expose
    val lowDay: Double?,

    @SerializedName("LASTMARKET")
    @Expose
    val lastMarket: String?,

    @SerializedName("OPENHOUR")
    @Expose
    val openHour: Double?,

    @SerializedName("HIGHHOUR")
    @Expose
    val highHourval: Double?,

    @SerializedName("LOWHOUR")
    @Expose
    val lowHour: Double?,

    @SerializedName("IMAGEURL")
    @Expose
    val imageUrl: String?
)

{
    fun getFormattedTime(): String {
       return convertTimestampToTime(lastUpdate)
    }

    fun getFullImageUrl (): String {
        return BASE_IMAGE_URL + imageUrl
    }
}