package com.irmak.themoviedc.holder

import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.TvRatedGridBinding
import com.irmak.themoviedc.model.tvTopRatedModel.TvTopRatedResponse
import com.irmak.themoviedc.ui.extensions.loadImage

class TvRatedViewHolder(val binding: TvRatedGridBinding):RecyclerView.ViewHolder(binding.root) {

    fun setTvRate(response:TvTopRatedResponse){
        binding.posterViewTvRate.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response.poster_path}")
        binding.txtTitleTvRate.text = response.name
    }
}