package com.boriskaloshin.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.boriskaloshin.cryptoapp.domain.GetCoinInfoListUseCase
import com.boriskaloshin.cryptoapp.domain.GetCoinInfoUseCase
import com.boriskaloshin.cryptoapp.domain.LoadDataUseCase
import com.boriskaloshin.cryptoapp.data.repository.CoinRepositoryImpl
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)
    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        viewModelScope.launch { loadDataUseCase() }
    }
}