package com.mycash.yajhaz.features.fragment.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycash.yajhaz.core.utils.CommonUtils.getLocalizedValue
import com.mycash.yajhaz.core.utils.CommonUtils.load
import com.mycash.yajhaz.databinding.ItemCategoriesBinding
import com.mycash.yajhaz.features.fragment.home.domain.model.base_category_entity.BaseCategoriesEntity

class CategoriesAdapter(private val categories: List<BaseCategoriesEntity>) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = categories[position]
        holder.bind(item)
    }

    override fun getItemCount() = categories.size

    inner class ViewHolder(private val binding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BaseCategoriesEntity) {
            binding.setUpViews(item)
        }

        private fun ItemCategoriesBinding.setUpViews(item: BaseCategoriesEntity) {
            categoriesIv.load(root.context, item.image)
            categoriesTv.text = getLocalizedValue(item.nameEn, item.nameAr)
        }
    }
}