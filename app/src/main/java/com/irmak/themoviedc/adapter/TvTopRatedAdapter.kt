package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.TvPagerLayoutBinding
import com.irmak.themoviedc.databinding.TvRatedGridBinding
import com.irmak.themoviedc.holder.TopRatedViewHolder
import com.irmak.themoviedc.holder.TvPopularViewHolder
import com.irmak.themoviedc.holder.TvRatedViewHolder
import com.irmak.themoviedc.model.tvPopularModel.TvPopularResponse
import com.irmak.themoviedc.model.tvTopRatedModel.TvTopRatedResponse

class TvTopRatedAdapter():RecyclerView.Adapter<TvRatedViewHolder>() {
    var tvTopRated: ArrayList<TvTopRatedResponse>? = null

    fun setListRateTv(tvTopRated: ArrayList<TvTopRatedResponse>){
        this.tvTopRated = tvTopRated
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvRatedViewHolder {
        val binding = TvRatedGridBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TvRatedViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return  tvTopRated!!.size
    }

    override fun onBindViewHolder(holder: TvRatedViewHolder, position: Int) {
        holder.setTvRate(tvTopRated!!.get(position))
    }
}