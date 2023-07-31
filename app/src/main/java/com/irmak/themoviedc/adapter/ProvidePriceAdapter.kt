package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentPriceDetailBinding
import com.irmak.themoviedc.holder.ProvidePriceViewHolder
import com.irmak.themoviedc.model.watchProviders.ProviderPriceResponse

class ProvidePriceAdapter:RecyclerView.Adapter<ProvidePriceViewHolder>() {
    var priceList:ArrayList<ProviderPriceResponse>?=null

    fun setProviderPriceList( priceList:ArrayList<ProviderPriceResponse>?){
        this.priceList = priceList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvidePriceViewHolder {
        val binding = FragmentPriceDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProvidePriceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProvidePriceViewHolder, position: Int) {
        holder.PriceBinder(priceList!!.get(position))
    }

    override fun getItemCount(): Int {
        return  priceList?.size ?:0
    }

}