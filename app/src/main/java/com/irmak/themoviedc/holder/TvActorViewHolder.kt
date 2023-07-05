package com.irmak.themoviedc.holder

import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentActorLayoutBinding
import com.irmak.themoviedc.model.tvActorModel.CastResponse
import com.irmak.themoviedc.ui.extensions.loadImage

class TvActorViewHolder(val binding:FragmentActorLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    fun tvActorBind(response:CastResponse) {
        binding.actorName.text = response.name
        binding.actorCharacter.text = response.character
        binding.actorPoster.loadImage("https://www.themoviedb.org/t/p/w300_and_h450_bestv2${response.profile_path}")
    }
}