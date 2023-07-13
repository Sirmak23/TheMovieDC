package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.ProvidersLayoutBinding
import com.irmak.themoviedc.holder.WatchProviderViewHolder
import com.irmak.themoviedc.model.watchProviders.Provider

class WatchProviderAdapter:RecyclerView.Adapter<WatchProviderViewHolder>() {
    var providerList:List<Provider>?=null
    fun setProvidersList(providerList:List<Provider>?){
        this.providerList = providerList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchProviderViewHolder {
        val binding = ProvidersLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WatchProviderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WatchProviderViewHolder, position: Int) {
        holder.provideBind(providerList!!.get(position))
    }

    override fun getItemCount(): Int {
        return providerList?.size ?:0
    }
}