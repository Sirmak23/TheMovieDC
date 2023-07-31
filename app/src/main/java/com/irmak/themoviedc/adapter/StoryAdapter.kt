package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentVideoLayoutBinding
import com.irmak.themoviedc.holder.StoryViewHolder
import com.irmak.themoviedc.model.nowPlayingModel.ResultNP
import com.irmak.themoviedc.model.storyModel.ResultStoryNP


class StoryAdapter: RecyclerView.Adapter<StoryViewHolder>() {
    var storyPlyList:ArrayList<com.irmak.themoviedc.model.storyModel.ResultStoryNP>? = null

    fun setStoryPlayList(storyPlyList:ArrayList<com.irmak.themoviedc.model.storyModel.ResultStoryNP>?){
        this.storyPlyList = storyPlyList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = FragmentVideoLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.storyBind(storyPlyList!!.get(position))
    }

    override fun getItemCount(): Int {
        return storyPlyList?.size ?:0
    }
}