package com.irmak.themoviedc.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentGridLayoutBinding
import com.irmak.themoviedc.holder.MovieViewHolder
import com.irmak.themoviedc.model.popularModel.MovieRespons
var GenreType:Int=0
class MovieAdapter:RecyclerView.Adapter<MovieViewHolder>() {

    var movieList: ArrayList<MovieRespons>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setList(movieList:ArrayList<MovieRespons>){
        this.movieList = movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = FragmentGridLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (GenreType == 1){
            return movieList!!.count { it.genre_ids!!.contains(80)}
        }else if (GenreType == 2){
            return  movieList!!.count { it.genre_ids!!.contains(80) || it.genre_ids.contains(28)  }
        }else
            return  movieList?.size ?:0

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList!!.get(position))
    }
}