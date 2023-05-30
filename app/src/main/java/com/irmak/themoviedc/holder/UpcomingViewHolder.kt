package com.irmak.themoviedc.holder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentUpcomingGridLayoutBinding
import com.irmak.themoviedc.model.UpcomingModel.ResultUP
import com.irmak.themoviedc.ui.extensions.loadImage

class UpcomingViewHolder(val binding: FragmentUpcomingGridLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun UpcomingBind(response:ResultUP){
        binding.posterViewUP.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response.poster_path}")
        binding.imdbPhotoUP.loadImage("https://m.media-amazon.com/images/G/01/imdb/images/social/imdb_logo._CB410901634_.png")
        binding.txtDateUP.text = response.release_date
        binding.txtTitleUP.text = response.title
        binding.txtVoteAvarageUP.text = "${response.vote_average}/10"
        binding.txtOverViewUP.text = response.overview
    }
}