package com.irmak.themoviedc.holder

import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.ProvidersLayoutBinding
import com.irmak.themoviedc.model.watchProviders.Provider
import com.irmak.themoviedc.ui.extensions.loadImage

class WatchProviderViewHolder(val binding:ProvidersLayoutBinding):RecyclerView.ViewHolder(binding.root) {
    fun provideBind(response: Provider){
        binding.posterViewProvider.loadImage("https://image.tmdb.org/t/p/original/${response.logo_path}")
    }
}