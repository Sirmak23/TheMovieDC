package com.irmak.themoviedc.holder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentDetailBinding
import com.irmak.themoviedc.databinding.FragmentPopularBinding
import com.irmak.themoviedc.model.popularModel.PopularMovieDetailResponse
import com.irmak.themoviedc.ui.extensions.loadImage

class PopularMovieViewHolder(val binding: FragmentDetailBinding):RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun MovieDetailBinder(response: PopularMovieDetailResponse) {
        binding.MovieDetailNameTextView.text = response.title
        binding.imdbDetailTextView.text = "${response.vote_average}/10"
        binding.DetailOzetTExt.text = response.overview
        binding.movieDEtailImageView.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response.poster_path}")

    }
}