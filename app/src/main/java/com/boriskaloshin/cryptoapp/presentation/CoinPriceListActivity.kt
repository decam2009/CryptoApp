package com.boriskaloshin.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.boriskaloshin.cryptoapp.presentation.adapter.CoinInfoAdapter
import com.boriskaloshin.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.boriskaloshin.cryptoapp.data.network.model.CoinInfoDto
import com.boriskaloshin.cryptoapp.domain.CoinInfo

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private var _binding: ActivityCoinPriceListBinding? = null
    private val binding: ActivityCoinPriceListBinding
        get() = _binding ?: throw RuntimeException("ActivityCoinPriceListBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val adapter = CoinInfoAdapter(applicationContext)
        adapter.onCoinInfoClickListener = object : CoinInfoAdapter.OnCoinInfoClickListener {
            override fun onClickListener(coinPriceInfo: CoinInfo) {
                val intent = CoinDetailActivity.newIntent(this@CoinPriceListActivity, coinPriceInfo.fromSymbol)
                startActivity(intent)
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.coinInfoList = it
        }
    }
}