package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.ProvidersLayoutBinding
import com.irmak.themoviedc.holder.TvWatchProviderViewHolder
import com.irmak.themoviedc.model.watchProviders.TvProvider

class TvWatchProviderAdapter : RecyclerView.Adapter<TvWatchProviderViewHolder>() {
    var tvProviderList: List<TvProvider>? = null
    fun setTvProvidersList(tvProviderList: List<TvProvider>?) {
        this.tvProviderList = tvProviderList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvWatchProviderViewHolder {
        val binding =
            ProvidersLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvWatchProviderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvWatchProviderViewHolder, position: Int) {
        holder.tvProvideBind(tvProviderList!!.get(position))
    }

    override fun getItemCount(): Int {
        return tvProviderList?.size ?: 0
    }
}