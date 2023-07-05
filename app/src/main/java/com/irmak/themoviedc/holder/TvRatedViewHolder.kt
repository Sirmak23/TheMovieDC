package com.irmak.themoviedc.holder

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.data.remote.api.tvIdNumber
import com.irmak.themoviedc.databinding.TvRatedGridBinding
import com.irmak.themoviedc.model.tvTopRatedModel.TvTopRatedResponse
import com.irmak.themoviedc.ui.Fragment.TvPopularFragmentDirections
import com.irmak.themoviedc.ui.extensions.loadImage

class TvRatedViewHolder(val binding: TvRatedGridBinding):RecyclerView.ViewHolder(binding.root) {

    fun setTvRate(response:TvTopRatedResponse){
        binding.posterViewTvRate.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response.poster_path}")
        binding.txtTitleTvRate.text = response.name
        binding.cardViewTvRate.setOnClickListener {
            tvIdNumber = response.id
            val action = TvPopularFragmentDirections.actionTvPopularFragmentToTvDetailFragment()
            itemView.findNavController().navigate(action)
        }
        }

}