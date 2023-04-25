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
    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(applicationContext)
        adapter.onCoinInfoClickListener = object : CoinInfoAdapter.OnCoinInfoClickListener {
            override fun onClickListener(coinPriceInfo: CoinInfo) {
                val intent = CoinDetailActivity.newIntent(this@CoinPriceListActivity, coinPriceInfo.fromSymbol)
                startActivity(intent)
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }
}