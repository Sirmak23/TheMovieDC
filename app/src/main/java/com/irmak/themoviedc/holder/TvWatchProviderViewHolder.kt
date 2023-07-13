package com.irmak.themoviedc.holder

import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.ProvidersLayoutBinding
import com.irmak.themoviedc.model.watchProviders.TvProvider
import com.irmak.themoviedc.ui.extensions.loadImage

class TvWatchProviderViewHolder(val binding: ProvidersLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    fun tvProvideBind(response:TvProvider){
        binding.posterViewProvider.loadImage("https://image.tmdb.org/t/p/original/${response.logo_path}")

    }
}