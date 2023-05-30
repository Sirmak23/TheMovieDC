package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentNowPlayingGridLayoutBinding
import com.irmak.themoviedc.holder.NowPlayingViewHolder
import com.irmak.themoviedc.model.nowPlayingModel.ResultNP


class NowPlayingAdapter: RecyclerView.Adapter<NowPlayingViewHolder>() {
    var nowPlyList:ArrayList<ResultNP>? = null

    fun setNowPlayList(nowPlayList:ArrayList<ResultNP>?){
        this.nowPlyList = nowPlayList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        val binding = FragmentNowPlayingGridLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NowPlayingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
    holder.NpBind(nowPlyList!!.get(position))
    }

    override fun getItemCount(): Int {
        return nowPlyList?.size ?:0
    }
}