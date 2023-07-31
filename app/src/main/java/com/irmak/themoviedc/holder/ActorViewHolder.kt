package com.irmak.themoviedc.holder

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.data.remote.api.personId
import com.irmak.themoviedc.databinding.FragmentActorLayoutBinding
import com.irmak.themoviedc.model.actorModel.ActorDetail
import com.irmak.themoviedc.ui.Fragment.DetailFragmentDirections
import com.irmak.themoviedc.ui.extensions.loadImage

class ActorViewHolder(val binding: FragmentActorLayoutBinding):RecyclerView.ViewHolder(binding.root) {


    fun actorBind(response: com.irmak.themoviedc.model.actorModel.ActorDetail) {
        binding.actorPoster.loadImage("https://www.themoviedb.org/t/p/w300_and_h450_bestv2${response.profile_path}")
        binding.actorName.text = response.name
        binding.actorCharacter.text = response.character
        binding.actorCard.setOnClickListener {
            personId = response.id
            val action = DetailFragmentDirections.actionDetailFragmentToActorMovieFragment()
            itemView.findNavController().navigate(action)
        }
    }
}