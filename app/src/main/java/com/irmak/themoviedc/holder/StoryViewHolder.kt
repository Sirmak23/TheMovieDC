package com.irmak.themoviedc.holder

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.R
import com.irmak.themoviedc.data.remote.api.movieIdNumber
import com.irmak.themoviedc.databinding.FragmentVideoLayoutBinding
import com.irmak.themoviedc.model.storyModel.ResultStoryNP
import com.irmak.themoviedc.ui.extensions.loadImage


class StoryViewHolder(val binding: FragmentVideoLayoutBinding): RecyclerView.ViewHolder(binding.root){

    fun storyBind(result: ResultStoryNP){
        binding.videoMoviePoster.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${result.poster_path}")
        binding.videoMovieName.text = result.title
        binding.trailerCardView.setOnClickListener {
            movieIdNumber = result.id
            itemView.findNavController().navigate(R.id.videoPlayerFragment)
        }
    }
}