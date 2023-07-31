package com.irmak.themoviedc.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.TvPagerLayoutBinding
import com.irmak.themoviedc.holder.TvPopularViewHolder
import com.irmak.themoviedc.model.tvPopularModel.TvPopularResponse

class TvPopularAdapter : RecyclerView.Adapter<TvPopularViewHolder>() {
    var tvList: ArrayList<com.irmak.themoviedc.model.tvPopularModel.TvPopularResponse>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setListTv(tvList: ArrayList<com.irmak.themoviedc.model.tvPopularModel.TvPopularResponse>) {
//        this.tvList = tvList
        if (this.tvList == null) {
            this.tvList = tvList
        } else if (this.tvList == tvList) {
        } else {
            this.tvList?.addAll(tvList)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvPopularViewHolder {
        val binding =
            TvPagerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvPopularViewHolder(binding)
    }

    override fun getItemCount(): Int {
//        return tvList?.size ?: 0
        var count = 0
        tvList?.forEach { currentItem ->
            if (currentItem.origin_country?.contains("IN") != true) {
                count++
            }
        }
        return count
    }

    override fun onBindViewHolder(holder: TvPopularViewHolder, position: Int) {
//        holder.tvbind(tvList!!.get(position))
        val currentItem = getNonINItem(position)
        holder.tvbind(currentItem)
    }

    private fun getNonINItem(position: Int): com.irmak.themoviedc.model.tvPopularModel.TvPopularResponse {
        var count = 0
        tvList?.forEach { currentItem ->
            if (currentItem.origin_country?.contains("IN") != true) {
                if (count == position) {
                    return currentItem
                }
                count++
            }
        }
        throw IllegalArgumentException("Invalid position: $position")
    }
}
