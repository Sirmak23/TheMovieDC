package com.irmak.themoviedc.holder

import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentNowPlayingGridLayoutBinding
import com.irmak.themoviedc.databinding.TvPagerLayoutBinding
import com.irmak.themoviedc.model.tvPopularModel.TvPopularResponse
import com.irmak.themoviedc.ui.extensions.loadImage

class TvPopularViewHolder(val binding:TvPagerLayoutBinding):RecyclerView.ViewHolder(binding.root) {
    fun tvbind(response:TvPopularResponse){
        binding.tvPopularImage.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response.poster_path}")
        binding.tvNameText.text = response.name
    }
}