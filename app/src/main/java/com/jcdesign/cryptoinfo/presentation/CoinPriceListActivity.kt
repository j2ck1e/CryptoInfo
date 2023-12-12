package com.jcdesign.cryptoinfo.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.jcdesign.cryptoinfo.R
import com.jcdesign.cryptoinfo.presentation.adapters.CoinInfoAdapter
import com.jcdesign.cryptoinfo.data.network.model.CoinInfoDto
import com.jcdesign.cryptoinfo.domain.CoinInfo

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener{
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }
        }
        val rvCoinPriceList = findViewById<RecyclerView>(R.id.rv_coinPriceList)
        rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        viewModel.coinInfoList.observe(this) {
            adapter.coinInfoList = it
        }


    }
}