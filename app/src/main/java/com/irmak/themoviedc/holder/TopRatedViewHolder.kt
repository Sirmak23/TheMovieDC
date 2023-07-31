package com.irmak.themoviedc.holder

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.data.remote.api.MovieOrTvID
import com.irmak.themoviedc.data.remote.api.movieIdNumber
import com.irmak.themoviedc.databinding.TopRatedLayoutBinding
import com.irmak.themoviedc.model.topRatedModel.topRatedResult
import com.irmak.themoviedc.ui.Fragment.PopularFragmentDirections
import com.irmak.themoviedc.ui.extensions.loadImage

class TopRatedViewHolder(val binding:TopRatedLayoutBinding ):RecyclerView.ViewHolder(binding.root) {

    fun topRatedBind(result: com.irmak.themoviedc.model.topRatedModel.topRatedResult) {
        binding.topRatedImage.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${result.poster_path}")
        binding.topRatedImage.setOnClickListener {
            movieIdNumber = result.id
            MovieOrTvID = result.id!!
            val action = PopularFragmentDirections.actionPopularFragmentToDetailFragment()
            itemView.findNavController().navigate(action)
        }
    }
}