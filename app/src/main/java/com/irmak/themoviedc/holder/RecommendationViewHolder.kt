package com.irmak.themoviedc.holder



import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.data.remote.api.tvIdNumber
import com.irmak.themoviedc.databinding.TvPagerLayoutBinding
import com.irmak.themoviedc.model.recommendationModel.RecomTvShow
import com.irmak.themoviedc.ui.Fragment.RecommendationFragment
import com.irmak.themoviedc.ui.Fragment.RecommendationFragmentDirections
import com.irmak.themoviedc.ui.Fragment.TvDetailFragmentDirections

import com.irmak.themoviedc.ui.extensions.loadImage

class RecommendationViewHolder(val binding: TvPagerLayoutBinding):RecyclerView.ViewHolder(binding.root) {
    fun recomBind(response: RecomTvShow){
        binding.tvPopularImage.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response.poster_path}")
        binding.tvNameText.text = response.name
//        binding.tvPopularImageCardview.setOnClickListener {
//            tvIdNumber = response.id
//            val action = TvDetailFragmentDirections.actionTvDetailFragmentSelf()
//            itemView.findNavController().navigate(action)
//        }
    }

}
