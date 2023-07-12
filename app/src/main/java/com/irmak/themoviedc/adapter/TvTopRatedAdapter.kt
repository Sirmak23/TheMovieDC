package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.TvRatedGridBinding
import com.irmak.themoviedc.holder.TvRatedViewHolder
import com.irmak.themoviedc.model.tvTopRatedModel.TvTopRatedResponse

class TvTopRatedAdapter : RecyclerView.Adapter<TvRatedViewHolder>() {
    var tvTopRated: ArrayList<TvTopRatedResponse>? = null

    fun setListRateTv(tvTopRated: ArrayList<TvTopRatedResponse>) {
//        this.tvTopRated = tvTopRated
        if (this.tvTopRated == null) {
            this.tvTopRated = tvTopRated
        } else {
            this.tvTopRated?.addAll(tvTopRated)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvRatedViewHolder {
        val binding = TvRatedGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvRatedViewHolder(binding)
    }

    override fun getItemCount(): Int {
//        return tvTopRated?.size ?: 0
        var count = 0
        tvTopRated?.forEach { currentItem ->
            if (currentItem.origin_country?.contains("JP") != true) {
                count++
            }
        }
        return count
    }

    override fun onBindViewHolder(holder: TvRatedViewHolder, position: Int) {
        val currentItem = getNonJpItem(position)
        holder.setTvRate(currentItem)
    }
    private fun getNonJpItem(position: Int): TvTopRatedResponse {
        var count = 0
        tvTopRated?.forEach { currentItem ->
            if (currentItem.origin_country?.contains("JP") != true) {
                if (count == position) {
                    return currentItem
                }
                count++
            }
        }
        throw IllegalArgumentException("Invalid position: $position")
    }
}