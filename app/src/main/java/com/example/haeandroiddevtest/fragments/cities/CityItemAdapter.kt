package com.example.haeandroiddevtest.fragments.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.haeandroiddevtest.databinding.CityItemViewBinding
import com.example.haeandroiddevtest.fragments.cities.CityItemAdapter.ViewHolder.Companion.from
import com.example.haeandroiddevtest.domain.ItemCity

class CityItemAdapter : ListAdapter<ItemCity, CityItemAdapter.ViewHolder>(DiffCallBack) {

    companion object DiffCallBack : DiffUtil.ItemCallback<ItemCity>() {
        override fun areItemsTheSame(oldItem: ItemCity, newItem: ItemCity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ItemCity, newItem: ItemCity): Boolean {
            return oldItem.city == newItem.city
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: CityItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = CityItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(city: ItemCity) {
            binding.varCity = city
        }
    }
}