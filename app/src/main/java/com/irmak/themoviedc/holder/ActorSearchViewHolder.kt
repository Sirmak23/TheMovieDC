package com.irmak.themoviedc.holder

import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.ActorSearchLayoutBinding
import com.irmak.themoviedc.databinding.FragmentActorSearchBinding
import com.irmak.themoviedc.model.search.Person
import com.irmak.themoviedc.ui.extensions.loadImage

class ActorSearchViewHolder(val binding: ActorSearchLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    fun ActorInfoBind(response: Person) {
        binding.txtNameActor.text = response.name
        binding.posterViewActor.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + response.profile_path)

        val movies: MutableList<String> = mutableListOf()
        response.known_for.forEach { movieName ->
            val movieTitle = movieName.original_title
            if (movieTitle.isNullOrEmpty().not()) {
                movies.add(movieTitle)
            }
        }

        val moviesText = movies.joinToString(", ")

        binding.txtMoviesofActor.text = moviesText
    }
}
