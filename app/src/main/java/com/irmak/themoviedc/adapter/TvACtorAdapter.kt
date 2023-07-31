package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentActorLayoutBinding
import com.irmak.themoviedc.holder.TvActorViewHolder
import com.irmak.themoviedc.model.tvActorModel.CastResponse

class TvACtorAdapter : RecyclerView.Adapter<TvActorViewHolder>() {

    var tvActorList: ArrayList<com.irmak.themoviedc.model.tvActorModel.CastResponse>? = null

    fun setTvActorLst(tvActorList: ArrayList<com.irmak.themoviedc.model.tvActorModel.CastResponse>) {
        this.tvActorList = tvActorList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvActorViewHolder {
        val binding =
            FragmentActorLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvActorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvActorViewHolder, position: Int) {
        holder.tvActorBind(tvActorList!!.get(position))
    }

    override fun getItemCount(): Int {
      return  tvActorList?.size ?: 0
    }
}