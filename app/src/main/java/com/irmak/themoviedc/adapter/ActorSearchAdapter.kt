package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.ActorSearchLayoutBinding
import com.irmak.themoviedc.databinding.FragmentActorSearchBinding
import com.irmak.themoviedc.holder.ActorSearchViewHolder
import com.irmak.themoviedc.model.search.Person

class ActorSearchAdapter:RecyclerView.Adapter<ActorSearchViewHolder>() {

    var actorMovieList: ArrayList<Person>? = null

    fun setActorMovie(actorMovieList: ArrayList<Person>?){
        this.actorMovieList = actorMovieList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorSearchViewHolder {
        val binding = ActorSearchLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ActorSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActorSearchViewHolder, position: Int) {
        holder.ActorInfoBind(actorMovieList!!.get(position))
    }

    override fun getItemCount(): Int {
        return actorMovieList!!.size
    }
}