package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentUpcomingGridLayoutBinding
import com.irmak.themoviedc.holder.UpcomingViewHolder
import com.irmak.themoviedc.model.UpcomingModel.ResultUP

class UpcomingAdapter : RecyclerView.Adapter<UpcomingViewHolder>() {

    var upComingList: ArrayList<com.irmak.themoviedc.model.UpcomingModel.ResultUP>? = null
    fun setUpcomingList(upComingList: ArrayList<com.irmak.themoviedc.model.UpcomingModel.ResultUP>) {
        this.upComingList = upComingList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val binding = FragmentUpcomingGridLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UpcomingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.UpcomingBind(upComingList!!.get(position))
    }

    override fun getItemCount(): Int {
        return upComingList?.size ?:0
    }
}