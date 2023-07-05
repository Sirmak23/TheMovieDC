package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentTvDetailBinding
import com.irmak.themoviedc.holder.TvDetailViewHolder
import com.irmak.themoviedc.model.tvDetailModel.TvDetailModel

class TvDetailAdapter:RecyclerView.Adapter<TvDetailViewHolder>() {

    var tvDetailList: ArrayList<TvDetailModel>? =null

    fun setTvList(tvDetailList: ArrayList<TvDetailModel>){
            this.tvDetailList = tvDetailList
            notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvDetailViewHolder {
        val binding = FragmentTvDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TvDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvDetailViewHolder, position: Int) {
        holder.tvDetailBind(tvDetailList!!.get(position))
    }

    override fun getItemCount(): Int {
        return tvDetailList?.size ?:0
    }
}
