package com.irmak.themoviedc.holder

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.data.remote.api.movieIdNumber
import com.irmak.themoviedc.data.remote.api.tvIdNumber
import com.irmak.themoviedc.databinding.TrendLayoutBinding
import com.irmak.themoviedc.model.trendAll.TrendResponse
import com.irmak.themoviedc.ui.Fragment.ChoiceVideo
import com.irmak.themoviedc.ui.Fragment.SearchFragmentDirections
import com.irmak.themoviedc.ui.extensions.loadImage

class TrendViewHolder(private val binding: TrendLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun trendBind(response: com.irmak.themoviedc.model.trendAll.TrendResponse) {
        if (response.media_type == "movie") {
            binding.txtTitleT.text = response.title
        } else if (response.media_type == "tv") {
            binding.txtTitleT.text = response.name
        }
//        binding.posterViewS.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${result.poster_path}")
        binding.posterViewT.loadImage("https://www.themoviedb.org/t/p/w533_and_h300_bestv2${response.backdrop_path}")
        binding.buttonTrendPlayTrailer.setOnClickListener{
            if (response.media_type == "movie") {
                ChoiceVideo = "movie"
                movieIdNumber = response.id
                val action = SearchFragmentDirections.actionSearchFragmentToVideoPlayerFragment()
                itemView.findNavController().navigate(action)
            } else if (response.media_type == "tv") {
                ChoiceVideo = "tv"
                tvIdNumber = response.id
                val action = SearchFragmentDirections.actionSearchFragmentToVideoPlayerFragment()
                itemView.findNavController().navigate(action)
            }
        }
        binding.cardViewT.setOnClickListener {
            if (response.media_type == "movie") {
                movieIdNumber = response?.id
                val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment()
                itemView.findNavController().navigate(action)
            } else if (response.media_type == "tv") {
                tvIdNumber = response.id
                val action = SearchFragmentDirections.actionSearchFragmentToTvDetailFragment()
                itemView.findNavController().navigate(action)
            }

        }
    }
}
