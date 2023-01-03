package com.example.haeandroiddevtest.fragments.apps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.haeandroiddevtest.databinding.AppItemViewBinding
import com.example.haeandroiddevtest.domain.ItemApp

class AppListItemAdapter(private val clickListener: AppItemListener) : ListAdapter<ItemApp, AppListItemAdapter.ViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(private val binding: AppItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = AppItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(appItem: ItemApp, clickListener: AppItemListener) {
            binding.apply {
                varAppItem = appItem
                varClickListener = clickListener
            }
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<ItemApp>() {
        override fun areItemsTheSame(oldItem: ItemApp, newItem: ItemApp): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ItemApp, newItem: ItemApp): Boolean {
            return oldItem.appName == newItem.appName
        }
    }
}

class AppItemListener(val clickListener: (appItem: ItemApp) -> Unit) {
    fun onClick(appItem: ItemApp) = clickListener(appItem)
}