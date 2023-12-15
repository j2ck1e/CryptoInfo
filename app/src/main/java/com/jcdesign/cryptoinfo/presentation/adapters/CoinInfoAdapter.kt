package com.jcdesign.cryptoinfo.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jcdesign.cryptoinfo.R
import com.jcdesign.cryptoinfo.databinding.ItemCoinInfoBinding
import com.jcdesign.cryptoinfo.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) :
    ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffCallback) {


    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding =
            ItemCoinInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        with(holder.binding) {
            val symbolsTemplate = context.resources.getString(R.string.symbols_template)
            val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
            tvSymbols.text = String.format(symbolsTemplate, coin.fromSymbol, coin.toSymbol)
            tvPrice.text = coin.price
            tvLastUpdate.text = String.format(lastUpdateTemplate, coin.lastUpdate)
            Picasso.get().load(coin.imageUrl).into(ivLogoCoin)
            root.setOnClickListener {
                onCoinClickListener?.onCoinClick(coin)
            }
        }
    }



    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinInfo)
    }
}