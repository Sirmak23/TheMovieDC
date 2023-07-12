package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentActorLayoutBinding
import com.irmak.themoviedc.databinding.FragmentActorMovieBinding
import com.irmak.themoviedc.databinding.TvRatedGridBinding
import com.irmak.themoviedc.holder.ActorMoviesViewHolder
import com.irmak.themoviedc.holder.ActorViewHolder
import com.irmak.themoviedc.model.actorModel.HisMovie

class ActorMoviesAdapter: RecyclerView.Adapter<ActorMoviesViewHolder>() {

    var actorMovieList: ArrayList<HisMovie>? = null

    fun setActorMoviesList(actorMovieList: ArrayList<HisMovie>?) {
        this.actorMovieList = actorMovieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorMoviesViewHolder {
        val binding =
            TvRatedGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActorMoviesViewHolder, position: Int) {
        holder.actorMovieBind(actorMovieList!!.get(position))
    }

    override fun getItemCount(): Int {
        return actorMovieList?.size ?: 0
    }

}