package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.TvRatedGridBinding
import com.irmak.themoviedc.holder.TvRatedViewHolder
import com.irmak.themoviedc.model.tvTopRatedModel.TvTopRatedResponse


class TvTopRatedAdapter : RecyclerView.Adapter<TvRatedViewHolder>() {
    private var tvTopRated: ArrayList<com.irmak.themoviedc.model.tvTopRatedModel.TvTopRatedResponse>? = null

    fun setListRateTv(tvTopRated: ArrayList<com.irmak.themoviedc.model.tvTopRatedModel.TvTopRatedResponse>) {
        this.tvTopRated = tvTopRated
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvRatedViewHolder {
        val binding = TvRatedGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvRatedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tvTopRated?.size ?: 0
    }

    override fun onBindViewHolder(holder: TvRatedViewHolder, position: Int) {
        holder.setTvRate(tvTopRated!!.get(position))
    }

}
