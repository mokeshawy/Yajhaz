package com.mycash.yajhaz.features.fragment.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycash.yajhaz.core.utils.CommonUtils.load
import com.mycash.yajhaz.databinding.ItemPopularNowBinding
import com.mycash.yajhaz.features.fragment.home.domain.model.trsnding_and_popular_sellers_entity.TrendingAndPopularSellersEntity

class PopularNowAdapter(private val trendingAndPopularSellers: List<TrendingAndPopularSellersEntity>) :
    RecyclerView.Adapter<PopularNowAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPopularNowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = trendingAndPopularSellers[position]
        holder.bind(item)
    }

    override fun getItemCount() = trendingAndPopularSellers.size

    inner class ViewHolder(private val binding: ItemPopularNowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrendingAndPopularSellersEntity) {
            binding.setupViews(item)
        }

        private fun ItemPopularNowBinding.setupViews(item: TrendingAndPopularSellersEntity) {
            popularNowIv.load(root.context, item.image)
            favoriteBtn.isChecked = item.isFavorite
            popularNameTv.text = item.name
        }
    }
}