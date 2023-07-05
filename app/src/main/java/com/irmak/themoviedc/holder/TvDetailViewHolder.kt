package com.irmak.themoviedc.holder

import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentTvDetailBinding
import com.irmak.themoviedc.databinding.FragmentTvPopularBinding
import com.irmak.themoviedc.databinding.TvPagerLayoutBinding
import com.irmak.themoviedc.model.tvDetailModel.TvDetailModel
import com.irmak.themoviedc.ui.extensions.loadImage

class TvDetailViewHolder(val binding: FragmentTvDetailBinding):RecyclerView.ViewHolder(binding.root) {

    fun tvDetailBind(response:TvDetailModel){
        binding.TvDetailNameTextView .text = response.name
        binding.imdbDetailTextView.text = "${response.vote_average}/10"
        binding.TvDetailOzetTExt.text = response.overview
        binding.tvDetailImageView.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response.poster_path}")
    }
}