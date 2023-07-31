package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentActorLayoutBinding
import com.irmak.themoviedc.holder.ActorViewHolder
import com.irmak.themoviedc.model.actorModel.ActorDetail

class ActorAdapter : RecyclerView.Adapter<ActorViewHolder>() {

    var actorDetailList: ArrayList<ActorDetail>? = null

    fun setActorList(actorDetailList: ArrayList<ActorDetail>?) {
        this.actorDetailList = actorDetailList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val binding =
            FragmentActorLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.actorBind(actorDetailList!!.get(position))
    }

    override fun getItemCount(): Int {
        return actorDetailList?.size ?: 0
    }

}