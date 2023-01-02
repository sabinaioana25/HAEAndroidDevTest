package com.example.haeandroiddevtest.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.haeandroiddevtest.databinding.AppItemViewBinding
import com.example.haeandroiddevtest.network.AppItem

class AppItemAdapter : ListAdapter<AppItem, AppItemAdapter.ViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: AppItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = AppItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(appItem: AppItem) {
            binding.varAppItem = appItem
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<AppItem>() {
        override fun areItemsTheSame(oldItem: AppItem, newItem: AppItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: AppItem, newItem: AppItem): Boolean {
            return oldItem.appName == newItem.appName
        }
    }
}