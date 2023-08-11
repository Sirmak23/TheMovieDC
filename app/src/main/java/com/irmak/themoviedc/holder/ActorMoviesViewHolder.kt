package com.irmak.themoviedc.holder

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.data.remote.api.MovieOrTvID
import com.irmak.themoviedc.data.remote.api.movieIdNumber
import com.irmak.themoviedc.data.remote.api.tvIdNumber
import com.irmak.themoviedc.databinding.TvRatedGridBinding
import com.irmak.themoviedc.model.actorModel.HisMovie
import com.irmak.themoviedc.ui.Fragment.ActorMovieFragmentDirections
import com.irmak.themoviedc.ui.extensions.loadImage

class ActorMoviesViewHolder(val binding:TvRatedGridBinding):RecyclerView.ViewHolder(binding.root) {
    fun actorMovieBind(response:HisMovie){
        binding.posterViewTvRate.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response.poster_path}")
        binding.txtTitleTvRate.text = response.title
        binding.cardViewTvRate.setOnClickListener {
            movieIdNumber = response.id
            MovieOrTvID = response.id!!
            val action = ActorMovieFragmentDirections.actionActorMovieFragmentToDetailFragment()
            itemView.findNavController().navigate(action)
        }
    }
}