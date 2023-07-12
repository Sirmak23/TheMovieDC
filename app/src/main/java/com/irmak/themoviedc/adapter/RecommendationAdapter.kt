package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.TvPagerLayoutBinding
import com.irmak.themoviedc.holder.RecommendationViewHolder
import com.irmak.themoviedc.model.recommendationModel.RecomTvShow

class RecommendationAdapter:RecyclerView.Adapter<RecommendationViewHolder>() {

    var recomList:ArrayList<RecomTvShow>? = null

    fun setRecommeList(recomList:ArrayList<RecomTvShow>?){
        this.recomList = recomList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        val binding = TvPagerLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecommendationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        holder.recomBind(recomList!!.get(position))
    }

    override fun getItemCount(): Int {
        return recomList?.size ?:0
    }
}