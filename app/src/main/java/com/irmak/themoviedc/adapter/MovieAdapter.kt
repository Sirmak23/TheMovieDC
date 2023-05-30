package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentGridLayoutBinding
import com.irmak.themoviedc.holder.MovieViewHolder
import com.irmak.themoviedc.model.popularModel.MovieRespons

class MovieAdapter:RecyclerView.Adapter<MovieViewHolder>() {

    var movieList: ArrayList<MovieRespons>? = null

    fun setList(movieList:ArrayList<MovieRespons>){
        this.movieList = movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = FragmentGridLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  movieList?.size ?:0
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList!!.get(position))
    }
}