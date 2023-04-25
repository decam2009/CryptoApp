package com.boriskaloshin.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.boriskaloshin.cryptoapp.data.database.AppDatabase
import com.boriskaloshin.cryptoapp.data.mapper.CoinMapper
import com.boriskaloshin.cryptoapp.data.network.ApiFactory
import com.boriskaloshin.cryptoapp.domain.CoinInfo
import com.boriskaloshin.cryptoapp.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    application: Application
) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService
    private val mapper = CoinMapper()
    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        val coinInfo: MutableList<CoinInfo> = mutableListOf()
        return MediatorLiveData<List<CoinInfo>>().apply {
            addSource(coinInfoDao.getPriceList()) { it ->
                it.map {
                    coinInfo.add(mapper.mapDbModelToEntity(it))
                }
                value = coinInfo
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return MediatorLiveData<CoinInfo>().apply {
            addSource(coinInfoDao.getPriceInfoAboutCoin(fromSymbol)) {
                value = mapper.mapDbModelToEntity(it)
            }
        }
    }

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo()
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
                TODO("Not yet implemented")
            }
            delay(10000)
        }
    }
}