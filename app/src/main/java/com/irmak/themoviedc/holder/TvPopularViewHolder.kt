package com.irmak.themoviedc.holder

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.data.remote.api.MovieOrTvID
import com.irmak.themoviedc.data.remote.api.tvIdNumber
import com.irmak.themoviedc.databinding.TvPagerLayoutBinding
import com.irmak.themoviedc.model.tvPopularModel.TvPopularResponse
import com.irmak.themoviedc.ui.Fragment.TvPopularFragmentDirections
import com.irmak.themoviedc.ui.extensions.loadImage

class TvPopularViewHolder(val binding:TvPagerLayoutBinding):RecyclerView.ViewHolder(binding.root) {
    fun tvbind(response: com.irmak.themoviedc.model.tvPopularModel.TvPopularResponse){
        binding.tvPopularImage.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response.poster_path}")
        binding.tvNameText.text = response.name
        binding.tvPopularImageCardview.setOnClickListener {
            tvIdNumber = response.id
            MovieOrTvID = response.id!!
            val action = TvPopularFragmentDirections.actionTvPopularFragmentToTvDetailFragment()
            itemView.findNavController().navigate(action)
        }

    }
}