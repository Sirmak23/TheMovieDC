package com.irmak.themoviedc.holder

import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentPriceDetailBinding
import com.irmak.themoviedc.model.watchProviders.ProviderPriceResponse

class ProvidePriceViewHolder(val binding:FragmentPriceDetailBinding):RecyclerView.ViewHolder(binding.root){
    fun PriceBinder(response: ProviderPriceResponse){
//        if (response.offers.get(0).monetization_type == "buy" &&  response.offers.get(0).presentation_type == "4k"){
//            binding.buy4kPrice.text = response.offers.get(0).retail_price.toString()
//        }
//        if (response.offers.get(0).monetization_type == "buy" &&  response.offers.get(0).presentation_type == "hd"){
//            binding.buyHdPrice.text = response.offers.get(0).retail_price.toString()
//        }
//        if (response.offers.get(0).monetization_type == "buy" &&  response.offers.get(0).presentation_type == "sd"){
//            binding.buySdPrice.text = response.offers.get(0).retail_price.toString()
//        }
    }
}