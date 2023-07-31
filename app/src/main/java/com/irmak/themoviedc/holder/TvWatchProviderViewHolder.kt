package com.irmak.themoviedc.holder

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.ProvidersLayoutBinding
import com.irmak.themoviedc.model.watchProviders.TvProvider
import com.irmak.themoviedc.ui.Fragment.DetailFragmentDirections
import com.irmak.themoviedc.ui.Fragment.TvDetailFragmentDirections
import com.irmak.themoviedc.ui.Fragment.layoutCheck
import com.irmak.themoviedc.ui.Fragment.providersId
import com.irmak.themoviedc.ui.extensions.loadImage

class TvWatchProviderViewHolder(val binding: ProvidersLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    fun tvProvideBind(response: com.irmak.themoviedc.model.watchProviders.TvProvider){
        binding.posterViewProvider.loadImage("https://image.tmdb.org/t/p/original/${response.logo_path}")

        binding.cardViewProvider.setOnClickListener {
            layoutCheck = 2
            providersId = response.provider_id
            val action = TvDetailFragmentDirections.actionTvDetailFragmentToPriceDetailFragment()
            binding.root.findNavController().navigate(action)
        }
    }
}