package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentDetailBinding
import com.irmak.themoviedc.holder.PopularMovieViewHolder
import com.irmak.themoviedc.model.popularModel.PopularMovieDetailResponse

class PopularMovieDetailAdapter:RecyclerView.Adapter<PopularMovieViewHolder>() {

    var popularMovieDetailList: ArrayList<com.irmak.themoviedc.model.popularModel.PopularMovieDetailResponse>? =null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val binding = FragmentDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PopularMovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return popularMovieDetailList?.size ?:0
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        holder.MovieDetailBinder(popularMovieDetailList!!.get(position))
    }
}