package com.irmak.themoviedc.holder

import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentActorLayoutBinding
import com.irmak.themoviedc.model.actorModel.ActorDetail
import com.irmak.themoviedc.ui.extensions.loadImage

class ActorViewHolder(val binding: FragmentActorLayoutBinding):RecyclerView.ViewHolder(binding.root) {


    fun actorBind(response: ActorDetail) {
        binding.actorPoster.loadImage("https://www.themoviedb.org/t/p/w300_and_h450_bestv2${response.profile_path}")
        binding.actorName.text = response.name
        binding.actorCharacter.text = response.character
    }
}