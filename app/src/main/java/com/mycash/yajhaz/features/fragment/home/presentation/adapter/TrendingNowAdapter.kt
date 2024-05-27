package com.mycash.yajhaz.features.fragment.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycash.yajhaz.core.utils.CommonUtils.load
import com.mycash.yajhaz.databinding.ItemTrendingNowBinding
import com.mycash.yajhaz.features.fragment.home.domain.model.trsnding_and_popular_sellers_entity.TrendingAndPopularSellersEntity

class TrendingNowAdapter(private val trendingAndPopularSellers: List<TrendingAndPopularSellersEntity>) :
    RecyclerView.Adapter<TrendingNowAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemTrendingNowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = trendingAndPopularSellers[position]
        holder.bind(item)
    }

    override fun getItemCount() = trendingAndPopularSellers.size

    inner class ViewHolder(private val binding: ItemTrendingNowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrendingAndPopularSellersEntity) {
            binding.trendingIv.load(binding.root.context, item.image)
        }

    }
}