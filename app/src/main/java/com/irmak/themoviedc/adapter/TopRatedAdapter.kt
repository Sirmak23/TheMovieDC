package com.irmak.themoviedc.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.irmak.themoviedc.databinding.TopRatedLayoutBinding
import com.irmak.themoviedc.holder.TopRatedViewHolder
import com.irmak.themoviedc.model.topRatedModel.topRatedResult

class TopRatedAdapter():RecyclerView.Adapter<TopRatedViewHolder>() {

     var topList:ArrayList<com.irmak.themoviedc.model.topRatedModel.topRatedResult>?= null

    @SuppressLint("NotifyDataSetChanged")
    fun setTList(topList:ArrayList<com.irmak.themoviedc.model.topRatedModel.topRatedResult>){
        this.topList = topList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val binding = TopRatedLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TopRatedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  topList!!.size
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        holder.topRatedBind(topList!!.get(position))
    }


}