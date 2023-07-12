package com.irmak.themoviedc.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.TvPagerLayoutBinding
import com.irmak.themoviedc.holder.TvPopularViewHolder
import com.irmak.themoviedc.model.tvPopularModel.TvPopularResponse

class TvPopularAdapter:RecyclerView.Adapter<TvPopularViewHolder>() {
    var tvList:ArrayList<TvPopularResponse>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setListTv(tvList:ArrayList<TvPopularResponse>){
        this.tvList = tvList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvPopularViewHolder {
        val binding = TvPagerLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TvPopularViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tvList?.size ?: 0
    }

    override fun onBindViewHolder(holder: TvPopularViewHolder, position: Int) {
        holder.tvbind(tvList!!.get(position))
    }
}
