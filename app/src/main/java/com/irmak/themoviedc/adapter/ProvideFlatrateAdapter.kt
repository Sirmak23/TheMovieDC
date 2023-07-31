package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.ProvidersLayoutBinding
import com.irmak.themoviedc.holder.ProvideFlatrateViewHolder
import com.irmak.themoviedc.model.watchProviders.Provider


class ProvideFlatrateAdapter : RecyclerView.Adapter<ProvideFlatrateViewHolder>() {
    var providerFlatrateList: List<Provider>? = null
    fun setProvidersList(providerFlatrateList: List<Provider>?) {
        this.providerFlatrateList = providerFlatrateList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvideFlatrateViewHolder {
        val binding =
            ProvidersLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProvideFlatrateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProvideFlatrateViewHolder, position: Int) {
        holder.provideBind(providerFlatrateList!!.get(position))
    }

    override fun getItemCount(): Int {
        return providerFlatrateList?.size ?: 0
    }
}