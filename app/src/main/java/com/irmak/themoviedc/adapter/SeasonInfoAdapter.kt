package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.TvEpisodesLayoutBinding
import com.irmak.themoviedc.holder.SeasonInfoViewHolder
import com.irmak.themoviedc.model.seasonInfoModel.EpisodeData

class SeasonInfoAdapter : RecyclerView.Adapter<SeasonInfoViewHolder>() {
    var seasonEpisodeList: ArrayList<com.irmak.themoviedc.model.seasonInfoModel.EpisodeData>? = null

    fun setSeasonList(seasonEpisodeList: ArrayList<com.irmak.themoviedc.model.seasonInfoModel.EpisodeData>?) {
        this.seasonEpisodeList = seasonEpisodeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonInfoViewHolder {
        val binding =
            TvEpisodesLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeasonInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeasonInfoViewHolder, position: Int) {
        holder.SeasonBind(seasonEpisodeList!!.get(position))
    }

    override fun getItemCount(): Int {
        return seasonEpisodeList?.size ?: 0
    }
}